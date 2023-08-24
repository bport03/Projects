#Brandon Portillo
#Basic tretis

import pygame
from random import choice,randrange
from copy import deepcopy


pygame.init()


titel=50
heigh=15
width=10
pixel=width*titel,heigh*titel
frame=60

screen= pygame.display.set_mode(pixel)
timer= pygame.time.Clock()

grid = [pygame.Rect(x * titel,y * titel,titel,titel) for x in range(width) for y in range(heigh)]

# speed of the figures falling
count,speed,limit=0,60,200
shapes = [[(-1, 0), (-2, 0), (0, 0), (1, 0)],
               [(0, -1), (-1, -1), (-1, 0), (0, 0)],
               [(-1, 0), (-1, 1), (0, 0), (0, -1)],
               [(0, 0), (-1, 0), (0, 1), (-1, -1)],
               [(0, 0), (0, -1), (0, 1), (-1, -1)],
               [(0, 0), (0, -1), (0, 1), (1, -1)],
               [(0, 0), (0, -1), (0, 1), (-1, 0)]]

field=[[0 for i in range(width)]for j in range(heigh)]

shapes =[[pygame.Rect(x+width//2,y +1,1,1)for x , y in sh]for sh in shapes]
figures = pygame.Rect(0,0,titel -2,titel-2)

figure = deepcopy (choice(shapes))
def border_left_right():
    if figure[i].x < 0 or figure[i].x> width-1:
        return False
    elif figure[i].y >heigh -1 or field[figure[i].y][figure[i].x]:
        return False
    return True
while True:
    dx=0
    rotate = False
    screen.fill(pygame.Color('black'))
    #CONTROLS

    for event in pygame.event.get():
        if event.type ==pygame.QUIT:
            exit()
        if event.type==pygame.KEYDOWN:
            if event.key== pygame.K_LEFT:
                dx=-1
            elif event.key==pygame.K_RIGHT:
                dx=1
            elif event.key==pygame.K_DOWN:
                limit=100
            elif event.key==pygame.K_UP:
                rotate=True


    # MOVE left to right
    old_figure_copy= deepcopy(figure)
    for i in range(4):
        figure[i].x += dx
        if not border_left_right():
            figure= deepcopy(old_figure_copy)
            break

    # move up and down
    count+=speed
    if count> limit:
        count=0
        old_figure_copy = deepcopy(figure)
        for i in range(4):
            figure[i].y += 1
            if not border_left_right():
                for i  in range(4):
                    field[old_figure_copy[i].y][old_figure_copy[i].x]=pygame.Color("white")
                figure = deepcopy(choice(shapes))
                limit=2000
                break
    #rotate
    center= figure[0]
    old_figure_copy = deepcopy(figure)
    if rotate:
        for i in range(4):
            x= figure[i].y - center.y
            y=figure[i].x-center.x
            figure[i].x=center.x-x
            figure[i].y=center.y-y

            if not border_left_right():
                figure = deepcopy(old_figure_copy)
                break
    #make the gride to make it more visable
    [pygame.draw.rect(screen,(40,40,40),sc,1)for sc in grid]

    # shapes

    for i in range(4):
        figures.x=figure[i].x*titel
        figures.y=figure[i].y*titel
        pygame.draw.rect(screen,pygame.Color('white'),figures)

    for y, raw in enumerate(field):
        for x, col in enumerate(raw):
            if col:
                figures.x, figures.y = x * titel , y * titel
                pygame.draw.rect(screen,col,figures)

    pygame.display.flip()
    timer.tick(frame)




