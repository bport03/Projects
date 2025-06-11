#Global script loaded at game start
extends Node

#player health
var health = 5
#max player health
var max_health = 5
#player experience
var exp: float = 1
#player level
var level = 1
#exp required for player to level
var exp_level = 1
#number of enemies to spawn
var spawn_count = 100
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
		if exp_level > 2 and abil_count < 4: 
			popup_abilities()
	#on health = 0 switches to game over scene
	if health == 0:
		get_tree().change_scene_to_file("res://GameOver/gam_over.tscn")
		

# ability popup code:
#
# dialog object for abilities pop-up menu
var dialog: AcceptDialog

# variables to hold the names and icons for abilities buttons in popup menu
var button1pic 
var button2pic 
var button3pic
var button4pic
var button1ability 
var button2ability
var button3ability
var button4ability

# variable to store the path of the icon, and name of the ability
var newPic : String
var newAbility : String

# keep track of the count of abilites that have been unlocked, and their name and position in hotbar
var abil_count = 0
var hotbar_abilities = ["empty", "empty", "empty", "empty", "potion"]

# flag for whether the ability is owned
var flame_owned = false
var whip_owned = false
var lightning_owned = false
var knife_owned = false

# variable to let the update UI process know that the player has chosen a new 
# ability, so that it does not try to update UI with no ability
var has_chosen_new = false

# function for the ability unlock popup after leveling up 
func popup_abilities (title: String='~Ability Unlock~') -> void:
	has_chosen_new = false
	# create new AcceptDialog object
	dialog = AcceptDialog.new ()
	# set the title
	dialog.title = title
	
	# create an HBoxContainter to hold the ability buttons in 
	var container = HBoxContainer.new()  
	
	# set abilities and get their matching icon for button varaibles
	button1ability = "Flame Thrower"
	button2ability = "Whip"
	button3ability = "Lightning"
	button4ability = "Golden Butter Knife"
	button1pic = "res://Assets/Abilities/1.png"
	button2pic = "res://Assets/Abilities/whip_icon.png"
	button3pic = "res://Assets/Abilities/lighting_icon.png"
	button4pic = "res://Assets/Abilities/BK-Icon.png"

	# Create the buttons and add them to the popup, making sure to associate
	# the correct function with the action of pressing the buttons:
	if not flame_owned:
		var button1 = Button.new()
		var loadedpic1 = load(button1pic)
		button1.icon = loadedpic1
		button1.text = button1ability
		button1.connect("pressed", Callable(self, "_on_button1_pressed"))
			# add to the container
		container.add_child(button1)  
	
	if not whip_owned:
		var button2 = Button.new()
		var loadedpic2 = load(button2pic)
		button2.icon = loadedpic2
		button2.text = button2ability
		button2.connect("pressed", Callable(self, "_on_button2_pressed"))
			# add to the container
		container.add_child(button2)  
	
	if not lightning_owned:
		var button3 = Button.new()
		var loadedpic3 = load(button3pic)
		button3.icon = loadedpic3
		button3.text = button3ability
		button3.connect("pressed", Callable(self, "_on_button3_pressed"))
			# add to the container
		container.add_child(button3)  
	
	if not knife_owned:
		var button4 = Button.new()
		var loadedpic4 = load(button4pic)
		button4.icon = loadedpic4
		button4.text = button4ability
		button4.connect("pressed", Callable(self, "_on_button4_pressed"))
			# add to the container
		container.add_child(button4)  
	
	# add the container as a child
	dialog.add_child(container)  
	
	# Remove the "OK" button
	dialog.get_ok_button().hide()  

	# display the popup
	add_child(dialog)
	# make sure it centered on the screen
	dialog.popup_centered()

# functions for the button presses, all do the same thing, but with 
# the corresponding picture and ability name.
# They will change the flag and tracking variables for the chosen ability
# in order to make the ability useable, and show up in the hotbar 
func _on_button1_pressed():
	newPic = button1pic
	newAbility = "flame"
	dialog.hide()
	has_chosen_new = true
	hotbar_abilities[abil_count] = "flame_throwing"
	abil_count+=1
	flame_owned = true

func _on_button2_pressed():
	newPic = button2pic
	newAbility = "whip"
	dialog.hide()
	has_chosen_new = true
	hotbar_abilities[abil_count] = newAbility
	abil_count+=1
	whip_owned = true

func _on_button3_pressed():
	newPic = button3pic
	newAbility = "lightning"
	dialog.hide()
	has_chosen_new = true
	hotbar_abilities[abil_count] = newAbility
	abil_count+=1
	lightning_owned = true

func _on_button4_pressed():
	newPic = button4pic
	newAbility = "knife"
	dialog.hide()
	has_chosen_new = true
	hotbar_abilities[abil_count] = "golden_butter_knife"
	abil_count+=1
	knife_owned = true
