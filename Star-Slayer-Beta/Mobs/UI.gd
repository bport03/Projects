extends AnimatedSprite2D

# function that plays the health UI of the passed health
func update_health(health):
	if health == 8:
		play("8_8")
	elif health == 7:
		play("7_8")
	elif health == 6:
		play("6_8")
	elif health == 5:
		play("5_8")
	elif health == 4:
		play("4_8")
	elif health == 3:
		play("3_8")
	elif health == 2:
		play("2_8")
	elif health == 1:
		play("1_8")
	else:
		play("0_8")

