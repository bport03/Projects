extends Control 


@onready var ResOptionButton = $Window/HBoxContainer/OptionButton
# Define a dictionary that maps resolution names to Vector2 objects representing resolution sizes.
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
    # Get the current resolution of the viewport.
	AddResolutions()

# Function to populate the OptionButton with resolution options.
func AddResolutions():

	var currentResolution = Vector2(get_viewport().get_size())

	var index = 0
	# Iterate through the dictionary and add resolutions to the OptionButton
	for r in Resolutions:
		ResOptionButton.add_item(r)

		if Resolutions[r] == currentResolution:
			ResOptionButton._select_int(index)

		index += 1
# Called every frame. 'delta' is the elapsed time since the previous frame.
func _process(_delta):
	pass

# Placeholder function for handling the window close requested event.
func _on_window_close_requested():
	pass # Replace with function body.

# Function triggered when an item is selected in the OptionButton.
func _on_option_button_item_selected(index):
    # Get the selected resolution from the dictionary based on the selected item's text.
	var selected_resolution = Resolutions[ResOptionButton.get_item_text(index)]
	if selected_resolution:
        # Set the game window's size to the selected resolution.
		DisplayServer.window_set_size(selected_resolution)

