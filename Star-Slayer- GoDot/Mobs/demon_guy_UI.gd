extends AnimatedSprite2D

# function that sets the health UI to the passed health
func update_health(health):
	if health == 2:
		play("2_2")
	elif health == 1:
		play("1_2")
	else:
		play("0_2")

