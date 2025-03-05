#Player.gd dictates the player actions

extends CharacterBody2D

# Array of size 2 that holds the x,y positions of the the players position
@export var char_pos = [0,0]
# Array of size 2 that holds the x,y positions of the OLD players position
var old_char_pos = [0,0]
# Used to hold the movement scale of the player
@export var scaling_factor = 64

# placeholder health var
@export var health = 100

# turn status
@export var turn_status = true

# variable to hold the max number of grid spaces the player can move
var max_moves : int = 1

# Entity ID for the player is always 1 (used in distinguising entities)
var player_id = 1

# Signal that is emitted upon movement that signifies the new postions of the player
signal action_taken_position_entity_ID(old_position, new_position, entity_id)

# Based on the actions of move_right, move_left, etc. moves the player and its
# its sprite to the corresponding position based on scaling factor
func _input(event):
	if turn_status:
		# trigger the event that the left mouse button was pressed (for future, may want to add a 
		# check to make sure the click was made on the gameboard and not UI)
		if event is InputEventMouseButton and event.pressed and event.button_index == MOUSE_BUTTON_LEFT:
			var pos = get_global_mouse_position()
			var x_pos : int
			var y_pos : int
			
			#old_char_pos[0] = char_pos[0]
			
			# if the mouse click coordinates were not exactly on the grid center, 
			# convert it to match the corresponding grid
			x_pos = int(round(pos.x / scaling_factor))
			y_pos = int(round(pos.y / scaling_factor))

			# Calculate the distance in terms of grid spaces
			var dist_x = abs(x_pos - (char_pos[0] / scaling_factor))
			var dist_y = abs(y_pos - (char_pos[1] / scaling_factor))
			
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
