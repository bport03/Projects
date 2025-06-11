extends Control

#Declare the OptionButton node using onready
@onready var ResOptionButton = $Panel/HBoxContainer/OptionButton

@export var manager : G



# Define a dictionary of resolutions, mapping resolution names to Vector2 sizes.
var Resolutions: Dictionary = {
	"3840x2160": Vector2(3840, 2160),
	"2560x1440": Vector2(2560, 1440),
	"1920x1080": Vector2(1920, 1080),
	"1366x768": Vector2(1366, 768),
	"1536x864": Vector2(1536, 864),
	"1280x720": Vector2(1280, 720),
	"1440x900": Vector2(1440, 900),
	"1600x900": Vector2(1600, 900),
	"1024x600": Vector2(1024, 600),
	"800x600": Vector2(800, 600)
}
# Called when the node enters the scene tree for the first time.
func _ready():
	# Call the function to add resolutions to the OptionButton
	AddResolutions()
	hide()
	# Connect to the 'toggle_game_pause' signal emitted by the 'manager' variable.
	manager.connect("toggle_game_pause", _on_game_manager_toggle_game_paused)

func _process(_delta):
	pass
	
	
# Function to add resolutions to the OptionButton.
func AddResolutions():
	var currentResolution = Vector2(get_viewport().get_size())
	var index = 0
	# Iterate through the dictionary and add resolutions to the OptionButton
	for r in Resolutions:
		ResOptionButton.add_item(r)
		# If the resolution matches the current resolution, select it in the OptionButton.
		if Resolutions[r] == currentResolution:
			ResOptionButton._select_int(index)

		index += 1

# Function triggered when an item is selected in the OptionButton.
func _on_option_button_item_selected(index):
	# Get the selected resolution from the dictionary
	var selected_resolution = Resolutions[ResOptionButton.get_item_text(index)]

#Set the game window's size based on the selected resolution
	if selected_resolution:
		DisplayServer.window_set_size(selected_resolution)

# Function triggered when the game manager toggles the game's pause state.
func _on_game_manager_toggle_game_paused(is_paused : bool):
	if(is_paused):
		# Show the control node if the game is paused.
		show()
	else:
		# Hide the control node if the game is resumed.
		hide()

# Function triggered when the "Quit" button is pressed.
func _on_quit_pressed():
	# Quit the game application.
	get_tree().quit()

# Function triggered when the "Resume" button is pressed.
func _on_resume_pressed():
	# Set the 'game_paused' property of the 'manager' to false.
	manager.game_paused=false
