extends Area2D

# Signal that is emitted upon movement that signifies the new postions of the enemy
signal action_taken(old_position, new_position, entity_id)

# variable to keep track of turn
var turn_status = false

# the enmies entity ID
var entity_ID = 0
# keeps track of old position so we can correctly update gameboard
var old_current_enemy_position = null
# holds the parent so we can use functions in main
var parent = get_parent()
# holds the new position we will move to
var new_position = null
# variable for heath
var health = 1

# move the enemy
func move_enemy():
	# if it's their turn (NOTE THIS IS JUST TO ENSURE CORRECT BEHAVIOR)
	if turn_status:
		# keep track of old enemy position
		old_current_enemy_position = new_position
		# get spot to move to
		new_position = get_parent().get_enemy_move(self)
		# if a spot was found
		if new_position != null:
			# move their
			self.position = new_position
			emit_signal("action_taken", old_current_enemy_position, self, new_position)
		else:
			#print("skipped move")
			pass

			
			
