extends Node2D


# load necessary scenes (every enemy and the player)
var player_scene = preload("res://Character/player.tscn")
var demon_guy = preload("res://Mobs/demon_guy.tscn")
var robo_agent = preload("res://Mobs/robo_agent.tscn")
var hover_bot = preload("res://Mobs/hover_bot.tscn")
var buba_beam = preload("res://Mobs/buba_beam.tscn")
var holy_robot = preload("res://Mobs/holy_robot.tscn")
var monolith = preload("res://Mobs/monolith.tscn")
var orb = preload("res://Mobs/orb.tscn")

# hold a link to the player
@onready var player = $Player  

# pre- determined width and height of the gameboard NOTE this is changeable 
var width = 500
var height = 500

# indicates when bosses are spawned
var boss_player_level_indicator = 1

# depicts the center of THE GAMEBOARD ONLY WORKS FOR SQUARE
var center = float(width) / 2.0

# vector that holds all the created enemies
var enemy_link_vector = []

# variable that holds the current player position ON THE GAME BOARD
var player_game_board_position = [250,250]

# game board that holds the position and entity ID of all entities
var game_board = []

# keeps track of the next free ID
var current_free_ID = 2

# sets the maximum amount of enemies that can be created
# tested up to 200 enemies 
var maximum_enenmies = 4

# used to create a randon number
var rng = RandomNumberGenerator.new()

# used for the enemy path finding
# Create an instance of AStarGrid2D
var astar_grid = AStarGrid2D.new()

#Turn Counter
var turn_count = 1

# variable to calc waittime between enemy movements
var wait_time = 0

# holds every preloaded enemy scene 
var enemy_scene_array = []

# variable to set max enemy count
var max_enemy_count = 100

# set up the gameboard before starting stuff (good for performance)
func _init():
	for x in range(width):
		game_board.append([])
		for y in range(height):
			game_board[x].append(0)
			
	# Initializes the players default position on the gameboard
	game_board[250][250] = 1
	
	# Set up your grid-based map
	astar_grid.region = Rect2i(0, 0, width, height)
	astar_grid.cell_size = Vector2(1, 1)
	astar_grid.update()
		
# get_enemy_move(enemy) returns the next node in the shortest path to the player
func get_enemy_move(enemy):
	# Set up your start and end points
	var start_point = Vector2i(get_entity_position_on_game_board(enemy))
	var end_point = Vector2i(get_entity_position_on_game_board(player))
	
	# Find the shortest path between start and end points
	var path = astar_grid.get_id_path(start_point, end_point)

	# Holds the index for the enemy to move to
	var next_point_index = null

	# Get the index of the next point in the path
	if path.size() > 1:
		next_point_index = path[1]
	else:
		return null
		
	# If a shortest path was found then make sure it's not where an enemy is
	if next_point_index != null:
		for i in enemy_link_vector:
			if next_point_index == Vector2i(get_entity_position_on_game_board(i)):
				# if enemy wants to move where one is return null to not move
				return null
	
	# JAMES THIS IS WHERE YOU GET IF A ENEMY MOVED ONTO THE PLAYER (ATTACKED)
	if next_point_index == Vector2i(get_entity_position_on_game_board(player)):
		if global_stats.health > 1:
			enemy.play_animation("attack")
			global_stats.health -= 1
			#print(player.health)
		else:
			global_stats.health -= 1
			print("GAME OVER")
		return null	
	
	# if it wasn't null then we actually get the index relative to the game board
	if next_point_index != null:
		next_point_index.x = (next_point_index.x - center) * 64
		next_point_index.y = (next_point_index.y - center) * 64
		
	return next_point_index


# _update_position simply takes the old and new positions of the entity and wipes old and sets the new to the entity_id
func _update_position(old_position, new_position, entity_id):
	# if the entity is the player
	if entity_id == 1:
		game_board[player_game_board_position[0]][player_game_board_position[1]] = 0
		game_board[(new_position[0]/64 + center)][(new_position[1]/64 + center)] = entity_id
		player_game_board_position[0] = (new_position[0]/64 + center)
		player_game_board_position[1] = (new_position[1]/64 + center)
		print(player_game_board_position)
	# if the entity is not the player
	else:
		# if the old position is null such as newly created we just update to their current position
		if old_position == null:
			game_board[(new_position[0]/64 + center)][(new_position[1]/64 + center)] = entity_id
		else:
			game_board[old_position[0]/ 64 + center][old_position[1] / 64 + center] = 0
			game_board[(new_position[0]/64 + center)][(new_position[1]/64 + center)] = entity_id
		
		
		
# gets the entity id at that board location
func abilities_helper(x, y):
	return game_board[x][y]
	
# helper to get the enemy attached to the passed entity id
func abilities_helper_get_enemy(id):
	for i in enemy_link_vector:
		if i.entity_ID == id:
			return i
			

#Gives nodes information about the space and what it contains, if anything
func board_value(x, y):
	#space is empty
	if game_board[x + 250][y + 250] == 0:
		return 0
	#space conatains the player
	if game_board[x + 250][y + 250] == 1:
		return 1
	#space contains enemy returns entity ID	
	if game_board[x + 250][y + 250] > 1:
		return game_board[x + 250][y + 250]

#Preforms the attack on enemy by player
func perform_attack_on_enemy(enemy_entity_ID, damage):
	#temp var for enemy vector id
	var temp
	#inderate through vector to find the target enemy
	for i in enemy_link_vector:
		if enemy_entity_ID == i.entity_ID:
			i.health -= damage
			i.play_animation("damaged")
			i.update_health()
			#oon zero health enemy is removed, and id freed
			if i.health <= 0:
				global_stats.exp += 1
				i.update_health()
				i.dying = true
				enemy_link_vector.erase(i)
				i.play_animation("death")
				_update_position(null, i.position, 0)

# get_entity_position_on_game_board(entity) helper method that returns the position of the entity on the game board
func get_entity_position_on_game_board(entity):
	return Vector2(entity.position.x /64 + center, entity.position.y /64 + center)

	
# run instance setup to initlize connections, variables, and start the game
func _ready():
	player.connect("action_taken_position_entity_ID", Callable(self, "_on_player_action_taken"))
	enemy_scene_array.append(demon_guy)
	enemy_scene_array.append(robo_agent)
	enemy_scene_array.append(hover_bot)
	enemy_scene_array.append(buba_beam)
	enemy_scene_array.append(holy_robot)
	enemy_scene_array.append(monolith)
	check_turn()
	

# check whos turn it is and print it out
func check_turn():
	if player.turn_status:
		print("player turn ", turn_count)
		#track player turn and attacks taken
		turn_count += 1
		player.attack_count = 0
		global_stats.update_stats()
	else:
		print("enemy turn")
		if enemy_link_vector.size() >= 1:
			wait_time = 1 / (1.5 * enemy_link_vector.size())
		else:
			wait_time = 1
		#for i in enemy_link_vector:
			#print(i.get_instance_id())
			
		# for each created enemy make them move
		for i in enemy_link_vector:
			await get_tree().create_timer(wait_time).timeout
			i.move_enemy()
			global_stats.update_stats()
			
		player.turn_status = true
		# after all enemies have moved recall to print the header
		check_turn()
			#enemy.move_enemy()

# on player signal action_taken_position_entity_ID this is called that updates the players new position and then ends players turn
func _on_player_action_taken(old_position, new_positon, player_id):
	# print("signal recieved")
	_update_position(old_position, new_positon, player_id)
	player.turn_status = false
	# Initiate the enemy turn
	#enemy.turn_status = true
	
	# For each enemy set their turn to true 
	for i in enemy_link_vector:
		i.turn_status = true
		
	# creates a new enemy on the end of the players turn
	if enemy_link_vector.size() < max_enemy_count:
		_create_enemy()
		
	check_turn()
	get_node("Player/Camera2D/Panel").update_cooldowns()

# on enemy signal action_taken this is called that ends the players turn
func _on_enemy_action_taken(old_position, current_enemy, new_positon):
	_update_position(old_position, new_positon, current_enemy.entity_ID)
	current_enemy.turn_status = false
	# Initiate the player turn
	#player.turn_status = true
	# check_turn()

# _get_spawn_location returns a vector on the gameboard around the player that isn't taken CURRENTLY if none found in search_range returns NULL
# currently places all free spots in an array and then picks a random one
func _get_spawn_location():
	var search_range = 5 
	var possible_locations = []
	var rand_int 
	
	# go through gameboard and get all the positions a enemy could spawn
	for x in range(player_game_board_position[0] - search_range, player_game_board_position[0] + search_range + 1):
		for y in range(player_game_board_position[1] - search_range, player_game_board_position[1] + search_range + 1):
			if x >= 0 && x < width && y >= 0 && y < height && game_board[x][y] == 0:
				possible_locations.append(Vector2(x, y))
				
	# if there is a spot to spawn pick a random one 
	if possible_locations.size() != 0:
		rand_int = rng.randi_range(0,possible_locations.size() - 1)
		return(Vector2(possible_locations[rand_int].x, possible_locations[rand_int].y))
	else:
		# if there isn't a space then return null (creating a enemy failed)
		return null
	

# Creates a new enemy and places them on the board and in our gameboard
func _create_enemy():
	var new_enemy
	
	# if it's a turn to spawn boss then spawn it else grab random enemy to spawn
	if boss_player_level_indicator != global_stats.level:
		boss_player_level_indicator = global_stats.level
		new_enemy = orb.instantiate()
	else:
		new_enemy = enemy_scene_array.pick_random().instantiate()
	
	# Use _get_spawn_location to find a suitable spawn position for the new enemy
	var spawn_position = _get_spawn_location()
	
	# if an enemy could be spawned within the search area then spawn them
	if spawn_position != null:
		new_enemy.position = Vector2((spawn_position.x - center) * 64, (spawn_position.y - center) * 64)
		new_enemy.entity_ID = current_free_ID
		current_free_ID += 1
		new_enemy.play_animation("idle")
		new_enemy.update_health()
		add_child(new_enemy)
		enemy_link_vector.append(new_enemy)
		# add enemy to the gameboard
		_update_position(null, new_enemy.position, new_enemy.entity_ID)
		new_enemy.connect("action_taken", Callable(self, "_on_enemy_action_taken"))
	else:
		print("No suitable spawn position found for the new enemy.")

