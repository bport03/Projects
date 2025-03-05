extends CanvasLayer

class_name G


# Define a signal named "toggle_game_pause" with a boolean parameter 'is_paused'.
signal toggle_game_pause(is_paused: bool)

# Define a boolean variable 'game_paused' with an initial value of 'false'.
var game_paused : bool = false:
	get: 
		# Getter function returns the value of 'game_paused'.
		return game_paused
	set(value):
		# Setter function sets the value of 'game_paused'.
		game_paused=value
		# Pause or resume the game using the 'get_tree().paused' property.
		get_tree().paused = game_paused
		# Emit the "toggle_game_pause" signal with the current 'game_paused' value.
		emit_signal("toggle_game_pause",game_paused)

# Function to handle input events.
func _input(event : InputEvent):
	if(event.is_action_pressed("ui_cancel")):
		# Toggle the 'game_paused' variable when the "ui_cancel" action is pressed.
		game_paused= !game_paused
