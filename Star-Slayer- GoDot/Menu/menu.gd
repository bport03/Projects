extends Control

@onready var popup= $ButtoOPTIONS/Settings/Window
# Called when the node enters the scene tree for the first time.
func _ready():
	pass


# Called every frame. 'delta' is the elapsed time since the previous frame.

func _process(_delta):
	pass

# Function triggered when the "Start" button is pressed.
func _on_button_start_pressed():
	get_tree().change_scene_to_file("res://Main/main.tscn")

# Function triggered when the "Quit" button is pressed.
func _on_button_quit_pressed():
	get_tree().quit()

# Function triggered when the "Options" button is pressed.
func _on_butto_options_pressed():
	popup.show()
	
	
# hide setting window 
func _on_window_close_requested():
	popup.hide()



