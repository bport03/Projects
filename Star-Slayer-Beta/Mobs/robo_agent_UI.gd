extends AnimatedSprite2D

# function that sets the health UI to the passed health
func update_health(health):
	if health == 3:
		play("3_3")
	elif health == 2:
		play("2_3")
	elif health == 1:
		play("1_3")
	else:
		play("0_3")

