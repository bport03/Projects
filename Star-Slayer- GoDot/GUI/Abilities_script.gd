extends Node

#player health
var health = 5
#player experience
var exp: float = 1
#player level
var level = 1
#exp required for player to level
var exp_level = 1
#number of enemies to spawn
var spawn_count = 3
#Offset for calculating the exp to display
var max_xp = 100

#function updates stats after enemy/player actions
func update_stats():
	#calculates current exp and updates when called
	if exp >= exp_level:
		exp = 0
		level += 1
		exp_level = level
		spawn_count = level + 1
		if exp_level > 2: popup_abilities()
	#on health = 0 switches to game over scene
	if health == 0:
		get_tree().change_scene_to_file("res://GameOver/gam_over.tscn")
		


# dialog object for abilities pop-up menu
var dialog: AcceptDialog
# variables to hold the names and icons for abilities
var button1pic 
var button2pic 
var button3pic
var button1ability 
var button2ability
var button3ability

@onready var addtogui = get_node("Panel")

func _ready():
	addtogui = get_node("Panel")



func popup_abilities (title: String='*Ability Unlock*') -> void:
	# create new AcceptDialog object
	dialog = AcceptDialog.new ()
	# set the title
	dialog.title = title
	# connect to modal_closed signal so that when dialog popup is closed, 
	# it is deleted as well
	dialog.connect('modal_closed', dialog.queue_free)
	
	# create an HBoxContainter to hold the ability buttons in 
	var container = HBoxContainer.new()  # Create a new container
	
	# randomly***** select abilities and get their matching icon
	button1ability = "boots"
	button2ability = "whip"
	button3ability = "smash"
	button1pic = preload("res://Assets/enemySwimming_1.png")
	button2pic = preload("res://Assets/whip.png")
	button3pic = preload("res://Assets/enemyFlyingAlt_1.png")

	var button1 = Button.new()
	button1.icon = button1pic
	button1.text = button1ability
	button1.connect("pressed", Callable(self, "_on_button1_pressed"))
	container.add_child(button1)  # Add the button to the container

	var button2 = Button.new()
	button2.icon = button2pic
	button2.text = button2ability
	button2.connect("pressed", Callable(self, "_on_button2_pressed"))
	container.add_child(button2)  # Add the button to the container
	
	var button3 = Button.new()
	button3.icon = button3pic
	button3.text = button3ability
	button3.connect("pressed", Callable(self, "_on_button3_pressed"))
	container.add_child(button3)  # Add the button to the container

	dialog.add_child(container)  # Add the container to the dialog

	dialog.get_ok_button().hide()  # Remove the "OK" button

	add_child(dialog)
	dialog.popup_centered()



func _on_button1_pressed():
	print("Ability 1 selected")
	print("Ability = ", button1ability)
	addtogui.update_ability(button1ability, button1pic)
	dialog.hide()

func _on_button2_pressed():
	print("Ability 2 selected")
	print("Ability = ", button2ability)
	addtogui.update_ability(button2ability, button2pic)
	dialog.hide()

func _on_button3_pressed():
	print("Ability 3 selected")
	print("Ability = ", button3ability)
	addtogui.update_ability(button3ability, button3pic)
	dialog.hide()
