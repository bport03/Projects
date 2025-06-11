#Player.gd dictates the player actions

extends CharacterBody2D
# Array of size 2 that holds the x,y positions of the the players position
@export var char_pos = [0,0]
# Array of size 2 that holds the x,y positions of the OLD players position
var old_char_pos = [0,0]
# Used to hold the movement scale of the player
@export var scaling_factor = 64

# placeholder health var
@export var health = global_stats.health

@export var inventory: Inventory

# turn status
@export var turn_status = true

# preload the abilites
var abilities_animation_scene = preload("res://animated_sprite_2d.tscn")

# hold all the abilities and if the player has them
var abilities_dictionary = {
	"whip" :{
		"name": "whip",
		"has": true,
		"tier" : 0,
		"cool-down" : 3
	},
	"flame_throwing" :{
		"name": "flame_throwing",
		"has": true,
		"tier" : 0,
		"cool-down" : 4
	},
	"lightning" :{
		"name": "lightning",
		"has": true,
		"tier" : 0,
		"cool-down" : 3
	},
	"golden_butter_knife" :{
		"name": "golden_butter_knife",
		"has": true,
		"tier" : 0,
		"cool-down" : 12
	},
	"health_potion" :{
		"name": "health_potion",
		"has": true,
		"tier" : 0,
		"cool-down" : 5
	}
}

# used to keep track of the selected ability on the hotbar
var current_selected_ability = ""

# used to hold if an ability is waiting for mouse input
var ability_hold = false

#attack counter
var attack_limit = 1
var attack_count = 0

# variable to hold the max number of grid spaces the player can move
var max_moves : int = 1

# Entity ID for the player is always 1 (used in distinguising entities)
var player_id = 1

# Signal that is emitted upon movement that signifies the new postions of the player
signal action_taken_position_entity_ID(old_position, new_position, entity_id)
	
# Helper to convert the game board index to a position on the gameboard	
func convert_game_board_index_to_position(x_game, y_game):
	var block_size = 64  # Adjust this if your block size is different
	var center = 250  # Replace this with your actual center calculation

	var x = (x_game - center) * block_size
	var y = (y_game - center) * block_size

	return Vector2(x, y)

# performs the ability by finding all the indexes to hit on the gameboard
# adding that for animations to play and another vector to hold which enemies to hit
# it then plays the ability animation on top of all of them and damages the enemy
func _perform_ability(dist_x, dist_y, ability_used):
	var animation_list = []
	var main = get_parent()
	var abilities_node = get_node("Abilities")
	var matrix = abilities_node.get_ability_matrix_external(ability_used, dist_x, dist_y) # Assuming a 5x5 matrix
	var player_position = main.get_entity_position_on_game_board(self)
	var enemies_to_hit = []
	var center_x = matrix.size() / 2
	var center_y = matrix[0].size() / 2
	var current_animation
	var count = 0
	var temp
	
	
	# determine what ability is being used and set up the animation for ontop of the player
	current_animation = abilities_animation_scene.instantiate()
	animation_list.append(current_animation)
	print(current_selected_ability)
	if current_selected_ability == "flame_throwing":
		current_animation.set_animation_to_play("flame_throwing_player")
	elif current_selected_ability == "golden_butter_knife":
		current_animation.set_animation_to_play("golden_butter_knife_player")
	elif current_selected_ability == "lightning":
		current_animation.set_animation_to_play("lightning_player")
	elif current_selected_ability == "whip":
		current_animation.set_animation_to_play("whip_player")
		
	# add the animation to main scene
	current_animation.position = self.position
	main.add_child(current_animation)

	# find all the positions in which to play the animation ontop of
	for i in range(matrix.size()):
		for j in range(matrix[0].size()):
			var check_x = player_position.x + i - center_x
			var check_y = player_position.y + j - center_y
			# Check if the position is within the valid bounds (500x500 game board)
			if check_x >= 0 && check_x < 500 && check_y >= 0 && check_y < 500:
				if matrix[j][i] == 1:
					current_animation = abilities_animation_scene.instantiate()
					animation_list.append(current_animation)
					current_animation.set_animation_to_play(current_selected_ability)
					current_animation.position = convert_game_board_index_to_position(check_x, check_y)
					main.add_child(current_animation)
					var board_value = main.abilities_helper(check_x, check_y)
					if board_value != 0 && board_value != 1:
						if enemies_to_hit.find(board_value) == -1:
							enemies_to_hit.append(board_value)
							#print("Invalid position at (" + str(check_x) + ", " + str(check_y) + ") with value " + str(board_value))
							
			
	# play the animation				
	for i in animation_list:
		i.play_animation()
		
	# if butter nice then do butter knife dmg otherwise do normal dmg
	if current_selected_ability != "golden_butter_knife":
		for i in enemies_to_hit:
			main.perform_attack_on_enemy(i, 1)
			
	else:
		for i in enemies_to_hit:
			main.perform_attack_on_enemy(i, 100)


# Based on the actions of move_right, move_left, etc. moves the player and its
# its sprite to the corresponding position based on scaling factor
func _input(event):
	var can_use_ability = false
	if turn_status:
		
		# if the clicked to use an ability in slot 1
		if event.is_action_pressed("ability_1") and get_node("Camera2D/Panel").is_ability_usable(global_stats.hotbar_abilities[0]):
			if global_stats.hotbar_abilities[0] != "empty":
				ability_hold = true
				current_selected_ability = global_stats.hotbar_abilities[0]
				
		# if the clicked to use an ability in slot 2
		elif event.is_action_pressed("ability_2") and get_node("Camera2D/Panel").is_ability_usable(global_stats.hotbar_abilities[1]):
			if global_stats.hotbar_abilities[1] != "empty":
				ability_hold = true
				current_selected_ability = global_stats.hotbar_abilities[1]
				
		# if the clicked to use an ability in slot 3
		elif event.is_action_pressed("ability_3") and get_node("Camera2D/Panel").is_ability_usable(global_stats.hotbar_abilities[2]):
			if global_stats.hotbar_abilities[2] != "empty":
				ability_hold = true
				current_selected_ability = global_stats.hotbar_abilities[2]
				
		# if the clicked to use an ability in slot 4
		elif event.is_action_pressed("ability_4") and get_node("Camera2D/Panel").is_ability_usable(global_stats.hotbar_abilities[3]):
			if global_stats.hotbar_abilities[3] != "empty":
				ability_hold = true
				current_selected_ability = global_stats.hotbar_abilities[3]
				
		# if the player clicked to use the health potion
		elif event.is_action_pressed("ability_5"):
			print("current_selected_ability = ", current_selected_ability)
			can_use_ability = get_node("Camera2D/Panel").is_ability_usable("health_potion")
			if can_use_ability:
				get_node("Camera2D/Panel").activate_cooldown("health_potion")
				var temp = abilities_animation_scene.instantiate()
				temp.set_animation_to_play("potion_player")
				temp.position = self.position
				get_parent().add_child(temp)
				temp.play_animation()
				global_stats.health = global_stats.max_health
			
				
		# if the player is attempting to use an ability (activated then clicked)
		if event is InputEventMouseButton and event.pressed and event.button_index == MOUSE_BUTTON_LEFT and ability_hold == true:
			var pos = get_global_mouse_position()
			var x_pos : int
			var y_pos : int
			# if the mouse click coordinates were not exactly on the grid center, 
			# convert it to match the corresponding grid
			x_pos = int(round(pos.x / scaling_factor))
			y_pos = int(round(pos.y / scaling_factor))
			
			# Calculate the distance in terms of grid spaces
			var dist_x = abs(x_pos - (char_pos[0] / scaling_factor))
			var dist_y = abs(y_pos - (char_pos[1] / scaling_factor))
			
			if dist_x <= 1 && dist_y <= 1:
				print("valid")
			
			# perform the actual ability
			_perform_ability(x_pos - (char_pos[0] / scaling_factor), y_pos - (char_pos[1] / scaling_factor), current_selected_ability)
			await get_tree().create_timer(.25).timeout
			ability_hold = false
			get_node("Camera2D/Panel").activate_cooldown(current_selected_ability)
			
				
		# trigger the event that the left mouse button was pressed (for future, may want to add a 
		# check to make sure the click was made on the gameboard and not UI)
		elif event is InputEventMouseButton and event.pressed and event.button_index == MOUSE_BUTTON_LEFT and ability_hold == false:
			var pos = get_global_mouse_position()
			var x_pos : int
			var y_pos : int
			var main = get_parent()
			# if the mouse click coordinates were not exactly on the grid center, 
			# convert it to match the corresponding grid
			x_pos = int(round(pos.x / scaling_factor))
			y_pos = int(round(pos.y / scaling_factor))

			# Calculate the distance in terms of grid spaces
			var dist_x = abs(x_pos - (char_pos[0] / scaling_factor))
			var dist_y = abs(y_pos - (char_pos[1] / scaling_factor))
			
			# if they clicked on the player
			if main.board_value(x_pos, y_pos) == 1:
				print('this is the player')
			
			# if they clicked onto a enemy
			elif main.board_value(x_pos, y_pos)  > 1:
				# if they clicked passed 1 tile
				if dist_x > max_moves or dist_y > max_moves:
					print("Can only attack at range 1")
				# if they clicked an enemy
				else:
					# if they have an attack still up
					if attack_count < attack_limit:
						main.perform_attack_on_enemy(main.board_value(x_pos, y_pos), 1)
						print("Player Attacking")
						attack_count += 1
					# can't attack limit reached
					else:
						print("Reached Attack Limit")
			
			# if they clicked to do a movement
			elif main.board_value(x_pos, y_pos) == 0:
				# Check if the move is valid
				if dist_x > max_moves or dist_y > max_moves:
					print("You can only move ", max_moves, " spaces, try again")
				else: 
					old_char_pos[0] = char_pos[0]
					old_char_pos[1] = char_pos[1]
					char_pos[0] = x_pos * scaling_factor
					char_pos[1] = y_pos * scaling_factor
					self.position = Vector2(char_pos[0], char_pos[1])
					emit_signal("action_taken_position_entity_ID", old_char_pos, char_pos, player_id)
	
