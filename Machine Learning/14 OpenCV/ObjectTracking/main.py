import cv2
from tracker import *

tracker = EuclideanDistTracker()

cap = cv2.VideoCapture("highway.mp4")

# Object detection from stable camera
object_detector = cv2.createBackgroundSubtractorMOG2(
    history=100, varThreshold=80)

while True:
    ret, frame = cap.read()
    frame = cv2.resize(frame, (720, 480))

    # Reign of interest
    height, width, _ = frame.shape
    roi = frame[240:480, 280:450]  # height, width

    # 1. Object detection
    mask = object_detector.apply(roi)
    _, mask = cv2.threshold(mask, 252, 255, cv2.THRESH_BINARY)
    contours, _ = cv2.findContours(
        mask, cv2.RETR_TREE, cv2.CHAIN_APPROX_SIMPLE)

    detections = []

    for cnt in contours:
        # Calculate area and remove small elements
        area = cv2.contourArea(cnt)
        if area > 100:
            # cv2.drawContours(roi, [cnt], -1, (0, 255, 0), 1)
            x, y, w, h = cv2.boundingRect(cnt)
            cv2.rectangle(roi, (x, y), (x+w, y+h), (0, 255, 0), 2)
            detections.append([x, y, w, h])

    # 2. Object tracking
    boxes_id = tracker.update(detections)
    for box_id in boxes_id:
        x, y, w, h, id = box_id
        cv2.putText(roi, str(id), (x, y-15),
                    cv2.FONT_HERSHEY_SIMPLEX, 1, (255, 0, 255), 1)
        cv2.rectangle(roi, (x, y), (x+w, y+h), (0, 255, 0), 2)

    cv2.imshow('Camera', frame)
    cv2.imshow('Mask', mask)
    cv2.imshow('ROI', roi)

    if cv2.waitKey(30) == ord('q'):
        break

cap.release()
cv2.destroyAllWindows()
