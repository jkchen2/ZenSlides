import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class ZenSlides {

    private static String currentSubject = "", oldSubject = "";
    private static int currentSlideNumber = 0, totalSlides = 0;
    private static final double MAX_ANIMATION_DURATION = 100.;
    private static final int DEFAULT_WIDTH = 800, DEFAULT_HEIGHT = 600;
    private static BufferedImage[] slideCache = null;
    private static boolean skipReleased = true; // Skip keys released
    private static double animationTime = 0.; // Animation animationTime in seconds

    public static int[] foregroundColor = {245, 245, 245}, // Grey 100
                 secondaryForegroundColor = {158, 158, 158}, // Grey 500
                 backgroundColor = {33, 33, 33}, // Grey 900
                 secondaryBackgroundColor = {66, 66, 66}; // Grey 800
    public static String defaultFont = "Arial-20";
    public static String monospaceFont = "Courier-20";

    public void startSlides(int totalContentSlides) {
        startSlides(DEFAULT_WIDTH, DEFAULT_HEIGHT, Zen.DEFAULT_OPTIONS, totalContentSlides);
    }

    public void startSlides(int width, int height, String options, int totalContentSlides) {
        totalSlides = totalContentSlides;
        slideCache = new BufferedImage[totalSlides + 1];
        Zen.create(width, height, Zen.DEFAULT_OPTIONS);
        Zen.flipBuffer();
        jumpTo(0); // Title

        int rightAlignCenter = width - 16*6;
        long clickTime = 0, lastClickTime = 0;
        int clickX;
        int clickY;
        boolean leftReleased = true;

        while (Zen.isRunning()) {

            Zen.sleep(8);

            clickTime = Zen.getMouseClickTime();
            if (!Zen.isVirtualKeyPressed(KeyEvent.VK_LEFT))
                leftReleased = true;
            if (!(Zen.isVirtualKeyPressed(KeyEvent.VK_SPACE) ||
                    Zen.isVirtualKeyPressed(KeyEvent.VK_RIGHT) ||
                    clickTime != lastClickTime))
                skipReleased = true;

            if (clickTime != lastClickTime) {
                clickX = Zen.getMouseClickX();
                clickY = Zen.getMouseClickY(); // @byang18
                lastClickTime = clickTime;
            } else {
                clickX = 0;
                clickY = 0;
            }

            if ((clickX >= rightAlignCenter - 16*5 && clickX <= rightAlignCenter - 16*3 &&
                    clickY <= height - 16 && clickY >= height - 48) ||
                    (Zen.isVirtualKeyPressed(KeyEvent.VK_LEFT) && leftReleased)) {
                leftReleased = false;
                previousSlide();
            } else if (((clickX >= rightAlignCenter + 16*3 && clickX <= rightAlignCenter + 16*5 &&
                    clickY <= height - 16 && clickY >= height - 48) ||
                    Zen.isVirtualKeyPressed(KeyEvent.VK_SPACE) ||
                    Zen.isVirtualKeyPressed(KeyEvent.VK_RIGHT)) && skipReleased) {
                skipReleased = false;
                nextSlide();
            }

        }
    }

    public boolean drawSlide(int slideNumber) {
        switch (slideNumber) {

            case 0:
                currentSubject = "Title";
                int offset = animateOffset();
                clearScreenAnimated(1);
                Zen.setFont("Arial-40");
                Zen.drawText("This is a title!", 30, offset+60);
                return animationTime >= 2; // 2 seconds

            default:
                System.out.println("Too far!");
        }
        return true; // Finished animations
    }

    public void jumpTo(int slideNumber) {
        jumpTo(slideNumber, true, false);
    }

    // Draw slides from the beginning if this is not the next slide
    public void jumpTo(int slideNumber, boolean animate, boolean recurse) {
        if (slideNumber == currentSlideNumber - 1) // Don't animate when going back through slides
            animate = false;
        if (!recurse)
            currentSlideNumber = slideNumber;
        if (slideCache[slideNumber] == null || animate) {

            if (slideNumber > 0)
                if (slideCache[slideNumber - 1] == null)
                    jumpTo(slideNumber - 1, false, true);

            // Draw slide or slide animation
            if (animate) {
                animationTime = 0.;
                boolean finished = false;
                long clickTime = 0, lastClickTime = 0;
                do {

                    if (slideNumber > 0)
                        Zen.drawImage(slideCache[slideNumber - 1], 0, 0);
                    finished = drawSlide(slideNumber);
                    displayNavigation();
                    Zen.sleep(16); // Attempt 60 FPS
                    animationTime += 0.0625; // 1. in 1 second

                    // Check for skips
                    clickTime = Zen.getMouseClickTime();
                    if (!(Zen.isVirtualKeyPressed(KeyEvent.VK_SPACE) ||
                            Zen.isVirtualKeyPressed(KeyEvent.VK_RIGHT) ||
                            lastClickTime != clickTime)) {
                        skipReleased = true;
                    } else if (skipReleased) {
                        skipReleased = false;
                        animationTime = MAX_ANIMATION_DURATION;
                    } else {
                        lastClickTime = clickTime;
                    }

                } while (!finished);
            } else {
                animationTime = MAX_ANIMATION_DURATION;
                drawSlide(slideNumber);
                displayNavigation();
            }

            slideCache[slideNumber] = Zen.getWindowScreenShot();

        } else { // Load slide from cache
            drawSlide(slideNumber); // Get subject
            // TODO: Potentially need a clear here
            Zen.drawImage(slideCache[slideNumber], 0, 0);
            Zen.flipBuffer();
        }

        if (!recurse)
            oldSubject = currentSubject;
    }

    public void nextSlide() {
        if (currentSlideNumber < totalSlides)
            jumpTo(currentSlideNumber + 1);
    }
    public void previousSlide() {
        if (currentSlideNumber > 0)
            jumpTo(currentSlideNumber - 1);
    }

    private static void displayNavigation() {
        int height = Zen.getZenHeight(), width = Zen.getZenWidth();
        int rightAlignCenter = width - 16*6;
        Zen.setFont("Arial-30");

        // Wipe navigation bar
        setColor(secondaryBackgroundColor);
        Zen.fillRect(0, height - 64, width, height);

        // Buttons and subject swap
        setColor(foregroundColor);
        if (!oldSubject.equals(currentSubject)) {
            int wipeOffset = animateOffset(1, 0, 2, 48) + 48;
            Zen.drawText(oldSubject, 16, height - 20 + wipeOffset);
            int textOffset = animateOffset(1, 0.8);
            Zen.drawText(currentSubject, textOffset + 16, height - 20);
        } else {
            Zen.drawText(currentSubject, 16, height - 20);
        }
        Zen.fillRect(width/2, 0, 1, 800); // TODO: Remove these
        Zen.fillRect(width/2 + width/4, 0, 1, 800); // TODO: Remove these
        Zen.fillRect(width/2 - width/4, 0, 1, 800); // TODO: Remove these
        Zen.fillRect(0, height - 32, 800, 1); // TODO: Remove these
        Zen.fillRect(rightAlignCenter - 16*5, height - 48, 32, 32);
        Zen.fillRect(rightAlignCenter + 16*3, height - 48, 32, 32);

        // Arrows
        setColor(backgroundColor);
        Zen.drawLine(rightAlignCenter - 16*5 + 8, height - 32, rightAlignCenter - 16*5 + 21, height - 40);
        Zen.drawLine(rightAlignCenter - 16*5 + 8, height - 32, rightAlignCenter - 16*5 + 21, height - 24);
        Zen.drawLine(rightAlignCenter + 16*3 + 9, height - 40, rightAlignCenter + 16*3 + 22, height - 32);
        Zen.drawLine(rightAlignCenter + 16*3 + 9, height - 24, rightAlignCenter + 16*3 + 22, height - 32);

        // Slide number
        setColor(foregroundColor);
        Zen.drawText("/", rightAlignCenter - 3, height - 23);
        Zen.setFont("Arial-20");
        String currentSlideString = Integer.toString(currentSlideNumber);
        String totalSlidesString = Integer.toString(totalSlides);
        if (currentSlideString.length() == 1)
            currentSlideString = "  " + currentSlideString;
        if (totalSlidesString.length() == 1)
            totalSlidesString = totalSlidesString + "  ";
        Zen.drawText(currentSlideString, rightAlignCenter - 35, height - 25);
        Zen.drawText(totalSlidesString, rightAlignCenter + 14, height - 25);

        Zen.flipBuffer();

    }

    public static void setSubject(String newSubject) {
        currentSubject = newSubject;
    }

    // Drawing helpers

    public static void clearScreen() {
        setColor(backgroundColor);
        Zen.fillRect(0, 0, Zen.getZenWidth(), Zen.getZenHeight());
        setColor(foregroundColor);
    }

    public static void clearScreenAnimated(int direction) {
        int startX = 0, startY = 0, width1 = 0, height1 = 0, width2 = 0, height2 = 0;
        int maxWidth = Zen.getZenWidth(), maxHeight = Zen.getZenHeight();
        int delta = 0;
        double percentage1 = 0., percentage2 = 0.;
        switch (direction) {
            case 1: // NS
                width1 = maxWidth; width2 = maxWidth;
                height1 = animateOffset(2, 0, 4, maxHeight) + maxHeight;
                height2 = animateOffset(2.5, 0, 3, maxHeight) + maxHeight;
                break;

            default: // WE
                height1 = maxHeight; height2 = maxHeight;
                width1 = animateOffset(1, 0, 4, maxWidth) + maxWidth;
                width2 = animateOffset(1.5, 0, 3, maxWidth) + maxWidth;
        }
        setColor(secondaryBackgroundColor);
        Zen.fillRect(startX, startY, width1, height1);
        setColor(backgroundColor);
        Zen.fillRect(startX, startY, width2, height2);
        setColor(foregroundColor);
    }

    public static void setColor(int[] rgbValues) {
        try {
            assert rgbValues.length == 3;
            for (int v : rgbValues)
                assert v >= 0 && v <= 255;
        } catch (AssertionError e) {
            System.out.println("RGB values must be 3 ints between 0 and 255 inclusive.");
        }
        Zen.setColor(rgbValues[0], rgbValues[1], rgbValues[2]);
    }

    // Animation helpers:

    public static double getAnimationTime() {
        return animationTime;
    }

    public static boolean animationTimeout(double duration) {
        return animationTime >= duration;
    }

    public static double animatePercentage(double duration, double delay) {
        return animatePercentage(duration, delay, 2);
    }

    public static double animatePercentage(double duration, double delay, int magnitude) {

        if (animationTime <= delay) // Animation hasn't started yet
            return 0.;
        else if (animationTime >= duration + delay) // Animation has finished
            return 1.;

        double progressPercent = (animationTime - delay) / duration;
        if (magnitude <= 0)
            return progressPercent; // Kind of pointless
        else
            return 1 - Math.pow(progressPercent - 1, 2 * magnitude);
    }

    public static int animateOffset() {
        return animateOffset(2, 0);
    }

    public static int animateOffset(double duration, double delay) {
        int defaultOffset = Zen.getZenWidth() * 2;
        return animateOffset(duration, delay, 2, defaultOffset);
    }

    public static int animateOffset(double duration, double delay, int magnitude, int maxOffset) {
        double percentage = animatePercentage(duration, delay, magnitude);
        return (int)(maxOffset*percentage - maxOffset);
    }

    public static void animateColor(double duration, double delay, int magnitude, int[] start, int[] finish) {
        double percentage = animatePercentage(duration, delay, magnitude);
        int[] transitions = new int[3];
        for (int it = 0; it < 3; it++)
            transitions[it] = (int)(start[it] + (finish[it] - start[it]) * percentage);
        setColor(transitions);
    }

}
