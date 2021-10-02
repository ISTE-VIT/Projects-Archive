import matplotlib.pylab as plt
import cv2
import numpy as np

def roi(img, vertices):
    mask = np.zeros_like(img)
    # channel_count = img.shape[2]
    match_mask_color = (255)
    cv2.fillPoly(mask, vertices, match_mask_color)
    masked_image = cv2.bitwise_and(img, mask)
    return masked_image

def draw_lines(img, lines):
    img = np.copy(img)
    line_image = np.zeros((img.shape[0], img.shape[1], 3), dtype = np.uint8) 
    for line in lines:
        for x1, y1, x2, y2 in line:
            cv2.line(line_image, (x1,y1), (x2,y2), (255,0,0), thickness=4)
    img = cv2.addWeighted(img, 0.8, line_image, 1, 0.0)        
    return img

image = cv2.imread('road5.png')
image = cv2.cvtColor(image, cv2.COLOR_BGR2RGB)

print(image.shape)
height = image.shape[0]
width = image.shape[1]
# (704, 1279, 3)

region_of_interest_vertices = [
    (0, height),
    (width/2, height/2),
    (width, height)
]


gray_cropped_image = cv2.cvtColor(image, cv2.COLOR_RGB2GRAY)
cannyImage = cv2.Canny(gray_cropped_image, 100, 120)
cropped_image = roi(cannyImage, np.array([region_of_interest_vertices], np.int32))

lines = cv2.HoughLinesP(cropped_image, 
                        rho = 2, 
                        theta = np.pi/60, 
                        threshold = 160, 
                        lines=np.array([]), 
                        minLineLength = 40, 
                        maxLineGap=25)

imageWithLines = draw_lines(image, lines)

# colorAddition = cv2.fillPoly(imageWithLines, np.array([region_of_interest_vertices], np.int32), (120,200,200))


# plt.imshow(colorAddition)
plt.imshow(imageWithLines)
plt.show()
