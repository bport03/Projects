extends Panel

# create the pic to be used when there is not an ability in a slot
var empty_ability_pic = preload("res://Assets/empty_ability.png")

# Define a reference to the VBoxContainer node.
@onready var slots = $Slots

# local (to script) variable to track if the player leveled up
var level =  2 
# variable to track the number of abilities the player has
var ability_count : int = 0

# track the "old" pic and "old" ability label
var old_pic = global_stats.newPic
var old_ability = global_stats.newAbility

# vars for setting the cooldown after use (aka max cooldown)
var max_flame_cooldown = 6
var max_whip_cooldown = 5
var max_lightning_cooldown = 4
var max_knife_cooldown = 10
var max_potion_cooldown = 3

# vars for tracking the current cooldown on abilities
var curr_flame_cooldown = 0
var curr_whip_cooldown = 0
var curr_lightning_cooldown = 0
var curr_knife_cooldown = 0
var curr_potion_cooldown = 0

# vars for storing slot by ability
var flame_slot
var whip_slot
var lightning_slot
var potion_slot = 4
var knife_slot

# vars to track if player has a specific ability
var has_flame = false
var has_whip = false
var has_knife = false
var has_lightning = false
var has_potion = true

# vars to hold the current pic paths of abilites (make sure we dont change a pic when not needed)
var empty_slot_path = "res://Assets/empty_ability.png"
var curr_knife_pic = empty_slot_path
var curr_flame_pic = empty_slot_path
var curr_whip_pic = empty_slot_path
var curr_lightning_pic = empty_slot_path
var curr_potion_pic = "res://Assets/Abilities/Potion1.png"

# vars to see if we just activated a cooldown. that way we dont decrement if so
var just_set_max_lightning = false
var just_set_max_flame = false
var just_set_max_whip = false
var just_set_max_knife = false
var just_set_max_potion = false

# array var to hold the file paths of the cooldown icon 
var cooldown_paths = ["res://Assets/cooldowns/1.png", "res://Assets/cooldowns/2.png", "res://Assets/cooldowns/3.png", "res://Assets/cooldowns/4.png", "res://Assets/cooldowns/5.png", "res://Assets/cooldowns/6.png", "res://Assets/cooldowns/7.png", "res://Assets/cooldowns/8.png", "res://Assets/cooldowns/9.png", "res://Assets/cooldowns/10.png", "res://Assets/cooldowns/11.png", "res://Assets/cooldowns/12.png"]

# check if there has been a level up from the player, and if so we need to update our
# abilities UI element since the player has chosen a new one
func _process(_delta):
	if global_stats.level > level and global_stats.level > 2 and global_stats.has_chosen_new == true and ability_count <=3:
		update_ability(global_stats.newAbility, global_stats.newPic)
		level = global_stats.level
		
# when game is launched, create the ability UI with all empty slots (default pic and label) and health potion slot
func _ready():
	slots = $Slots
	# initialize all slots as empty
	for slot in slots.get_children():
		slot.get_node("Label").text = "  empty"
		slot.get_node("TextureRect").texture = empty_ability_pic
	# except for the potion slot as it will always be potion
	$Slots/VBoxContainer5.get_node("Label").text = "   Heal"
	$Slots/VBoxContainer5.get_node("TextureRect").texture = load("res://Assets/Abilities/Potion1.png")

# func to update ability after the popup has ran and player has chosen
func update_ability(ability, texture):
	# load the picture of the ability that was chosen
	var abilPic = load(texture)
	# var to hold the slot of UI element we are going to use
	var slot 
	# depending on how many abilities are owned, get the right slot to update
	if(ability_count == 0):
		slot = $Slots/VBoxContainer1
	elif(ability_count == 1):
		slot = $Slots/VBoxContainer2
	elif(ability_count == 2):
		slot = $Slots/VBoxContainer3
	elif(ability_count == 3):
		slot = $Slots/VBoxContainer4
	
	# Set the text of the corresponding label to the ability name,
	# and set the texture of the corresponding icon to the ability sprite.
	slot.get_node("Label").text = ability  
	slot.get_node("TextureRect").texture = abilPic
	
	# update that we have the chosen ability, and update its current pic path and set slot
	if ability == "flame":
		flame_slot = ability_count
		has_flame = true
		curr_flame_pic = texture
	elif ability == "whip":
		whip_slot = ability_count
		has_whip = true
		curr_whip_pic = texture
	if ability == "lightning":
		lightning_slot = ability_count
		has_lightning = true
		curr_lightning_pic = texture
	elif ability == "knife":
		knife_slot = ability_count
		has_knife = true
		curr_knife_pic = texture
	
	# only increment go up to num of slots available
	if ability_count < 4: 
		ability_count = ability_count + 1

# function is given an abilities name, and will return false if it has 
# a current cooldown val greater than 0, and false otherwise
func is_ability_usable(ability_name):
	if ability_name == "flame_throwing":
		if curr_flame_cooldown > 0:
			return false
		else:
			return true 
	elif ability_name == "whip":
		if curr_whip_cooldown > 0:
			return false
		else:
			return true
	elif ability_name == "lightning":
		if curr_lightning_cooldown > 0:
			return false
		else:
			return true
	elif ability_name == "golden_butter_knife":
		if curr_knife_cooldown > 0:
			return false
		else:
			return true
	elif ability_name == "health_potion":
		if curr_potion_cooldown > 0:
			return false
		else:
			return true

# function to change the cooldown icon when updating the cooldowns
# at the end of each use
func apply_cooldown_icon(slot_num, cooldown_num):
	var slot
	# load corresponding cooldown pic
	var cooldown_pic = load(cooldown_paths[cooldown_num - 1])
	
	# get correct slot
	if(slot_num == 0):
		slot = $Slots/VBoxContainer1
	elif(slot_num == 1):
		slot = $Slots/VBoxContainer2
	elif(slot_num == 2):
		slot = $Slots/VBoxContainer3
	elif(slot_num == 3):
		slot = $Slots/VBoxContainer4
	elif(slot_num == 4):
		slot = $Slots/VBoxContainer5
	
	# set the cooldown pic
	slot.get_node("TextureRect").texture = cooldown_pic

# func to apply the ability icon after a cooldown has finished 
func apply_abil_icon(slot_num, pic_path):
	var slot
	# load given pic path
	var pic = load(pic_path)
	# get correct slot
	if(slot_num == 0):
		slot = $Slots/VBoxContainer1
	elif(slot_num == 1):
		slot = $Slots/VBoxContainer2
	elif(slot_num == 2):
		slot = $Slots/VBoxContainer3
	elif(slot_num == 3):
		slot = $Slots/VBoxContainer4
	elif(slot_num == 4):
		slot = $Slots/VBoxContainer5
	# set slots new pic
	slot.get_node("TextureRect").texture = pic

# func to start the cooldown after using ability, uses ability name
func activate_cooldown(ability_name):
	var cooldown_num
	var slot_num
	# get the max cooldown num for given ability and get its slot number
	# as well as the flag to indicate we just set the cooldown
	if ability_name == "flame_throwing":
		cooldown_num = max_flame_cooldown
		curr_flame_cooldown = max_flame_cooldown
		slot_num = flame_slot
		just_set_max_flame = true
	elif ability_name == "whip":
		cooldown_num = max_whip_cooldown
		curr_whip_cooldown = max_whip_cooldown
		slot_num = whip_slot
		just_set_max_whip = true
	elif ability_name == "lightning":
		cooldown_num = max_lightning_cooldown
		curr_lightning_cooldown = max_lightning_cooldown
		slot_num = lightning_slot
		just_set_max_lightning = true
	elif ability_name == "golden_butter_knife":
		cooldown_num = max_knife_cooldown
		curr_knife_cooldown = max_knife_cooldown
		slot_num = knife_slot
		just_set_max_knife = true
	elif ability_name == "health_potion":
		cooldown_num = max_potion_cooldown
		curr_potion_cooldown = max_potion_cooldown
		slot_num = potion_slot
		just_set_max_potion = true
	# load the corresponding cooldown picture
	var cooldown_pic = load(cooldown_paths[cooldown_num])
	# call the func to apply the cooldown new icon
	apply_cooldown_icon(slot_num, cooldown_num)
	
# func to get called after each move the player makes, in order to decrement the cooldown
# at the end of each turn
func update_cooldowns():
	var updated_icon
	var slot_num
	var slot
	
	# update flame cooldowns
	# if 0, and the icon is not already set to normal, or empty, put normal icon
	if curr_flame_cooldown == 0 and has_flame and curr_flame_pic != empty_slot_path and curr_flame_pic != "res://Assets/Abilities/1.png":
		updated_icon = "res://Assets/Abilities/1.png"
		slot_num = flame_slot
		apply_abil_icon(slot_num, updated_icon)
	# if cooldown active, and not first cooldown round, decrement the count and update the pic
	elif curr_flame_cooldown > 1 and has_flame and not just_set_max_flame:
		curr_flame_cooldown -= 1
		slot_num = flame_slot
		apply_cooldown_icon(slot_num, curr_flame_cooldown)
	# if we are on last cooldown round, decrement and reset icon to normal
	elif curr_flame_cooldown == 1 and has_potion and not just_set_max_flame:
		curr_flame_cooldown -= 1
		updated_icon = "res://Assets/Abilities/1.png"
		slot_num = flame_slot
		curr_flame_pic = updated_icon
		apply_abil_icon(slot_num, updated_icon)
	
	# update whip cooldowns
	# if 0, and the icon is not already set to normal, or empty, put normal icon
	if curr_whip_cooldown == 0 and has_whip and curr_whip_pic != empty_slot_path and curr_whip_pic != "res://Assets/Abilities/whip_icon.png":
		updated_icon = "res://Assets/Abilities/whip_icon.png"
		slot_num = whip_slot
		apply_abil_icon(slot_num, updated_icon)
	# if cooldown active, and not first cooldown round, decrement the count and update the pic
	elif curr_whip_cooldown > 1 and has_whip and not just_set_max_whip:
		curr_whip_cooldown -= 1
		slot_num = whip_slot
		apply_cooldown_icon(slot_num, curr_whip_cooldown)
	# if we are on last cooldown round, decrement and reset icon to normal
	elif curr_whip_cooldown == 1 and has_potion and not just_set_max_whip:
		curr_whip_cooldown -= 1
		updated_icon = "res://Assets/Abilities/whip_icon.png"
		slot_num = whip_slot
		curr_whip_pic = updated_icon
		apply_abil_icon(slot_num, updated_icon)
	
	# update lightning cooldowns
	# if 0, put normal icon# if 0, and the icon is not already set to normal, or empty, put normal icon
	if curr_lightning_cooldown == 0 and has_lightning and curr_lightning_pic != empty_slot_path and curr_lightning_pic != "res://Assets/Abilities/lighting_icon.png":
		updated_icon = "res://Assets/Abilities/lighting_icon.png"
		slot_num = lightning_slot
		apply_abil_icon(slot_num, updated_icon)
	# if cooldown active, and not first cooldown round, decrement the count and update the pic
	elif curr_lightning_cooldown > 1 and has_lightning and not just_set_max_lightning:
		curr_lightning_cooldown -= 1
		slot_num = lightning_slot
		apply_cooldown_icon(slot_num, curr_lightning_cooldown)
	# if we are on last cooldown round, decrement and reset icon to normal
	elif curr_lightning_cooldown == 1 and has_lightning and not just_set_max_lightning:
		curr_lightning_cooldown -= 1
		updated_icon = "res://Assets/Abilities/lighting_icon.png"
		slot_num = lightning_slot
		curr_lightning_pic = updated_icon
		apply_abil_icon(slot_num, updated_icon)
	
	# update knife cooldowns
	# if 0, and the icon is not already set to normal, or empty, put normal icon
	if curr_knife_cooldown == 0 and has_knife and curr_knife_pic != empty_slot_path and curr_knife_pic != "res://Assets/Abilities/BK-Icon.png":
		updated_icon = "res://Assets/Abilities/BK-Icon.png"
		slot_num = knife_slot
		apply_abil_icon(slot_num, updated_icon)
	# if cooldown active, and not first cooldown round, decrement the count and update the pic
	elif curr_knife_cooldown > 1 and has_knife and not just_set_max_knife:
		curr_knife_cooldown -= 1
		slot_num = knife_slot
		apply_cooldown_icon(slot_num, curr_knife_cooldown)
	# if we are on last cooldown round, decrement and reset icon to normal
	elif curr_knife_cooldown == 1 and has_knife and not just_set_max_knife:
		curr_knife_cooldown -= 1
		updated_icon = "res://Assets/Abilities/BK-Icon.png"
		slot_num = knife_slot
		curr_knife_pic = updated_icon
		apply_abil_icon(slot_num, updated_icon)
	
	# update potion cooldowns
	# if 0, and the icon is not already set to normal, or empty, put normal icon
	if curr_potion_cooldown == 0 and has_potion and curr_potion_pic != empty_slot_path and curr_potion_pic != "res://Assets/Abilities/Potion1.png":
		updated_icon = "res://Assets/Abilities/Potion1.png"
		slot_num = potion_slot
		curr_potion_pic = updated_icon
		apply_abil_icon(slot_num, updated_icon)
	# if cooldown active, and not first cooldown round, decrement the count and update the pic
	elif curr_potion_cooldown > 1 and has_potion and not just_set_max_potion:
		curr_potion_cooldown -= 1
		slot_num = potion_slot
		curr_potion_pic = cooldown_paths[curr_potion_cooldown]
		apply_cooldown_icon(slot_num, curr_potion_cooldown)
	# if we are on last cooldown round, decrement and reset icon to normal
	elif curr_potion_cooldown == 1 and has_potion and not just_set_max_potion:
		curr_potion_cooldown -= 1
		updated_icon = "res://Assets/Abilities/Potion1.png"
		slot_num = potion_slot
		curr_potion_pic = updated_icon
		apply_abil_icon(slot_num, updated_icon)
	
	# if this was the first round of a cooldown, change the flag to indicate it is no longer
	# the cooldowns first round
	if curr_flame_cooldown == max_flame_cooldown:
		just_set_max_flame = false
	if curr_knife_cooldown == max_knife_cooldown:
		just_set_max_knife = false
	if curr_lightning_cooldown == max_lightning_cooldown:
		just_set_max_lightning = false
	if curr_potion_cooldown == max_potion_cooldown:
		just_set_max_potion = false
	if curr_whip_cooldown ==  max_whip_cooldown:
		just_set_max_whip = false

