public class RecursionDemo extends ZenSlides {

    int[][] palette = {
        {144, 202, 249}, // Blue 200
        {165, 214, 167}, // Green 200
        {255, 204, 128}  // Orange 200
    };

    int[] offsets = new int[7]; // 7 concurrent animations
    
    public static String defaultFont = "DejaVu Sans-20";
    public static String monospaceFont = "DejaVu Sans Mono-20";

    public static void main(String[] args) {
        ZenSlides.loadFont("DejaVuSans.ttf");
        ZenSlides.loadFont("DejaVuSansMono.ttf");
        RecursionDemo presentation = new RecursionDemo();
        presentation.startSlides(18);
    }

    public boolean drawSlide(int slideNumber) {
        Zen.setFont(defaultFont);

        switch (slideNumber) {

            case 0:
                ZenSlides.setSubject("Title");
                offsets[0] = ZenSlides.animateOffset(); // Animate from negative window width to 0 in 2 seconds
                ZenSlides.clearScreen(); // Instant screen clear
                Zen.setFont("DejaVu Sans-40");
                Zen.drawText("Recursion with (simplified) factorials", 30, offsets[0]+120);
                ZenSlides.setColor(secondaryForegroundColor);
                Zen.setFont("DejaVu Sans Mono-30");
                for (int it = 0; it < 5; it++) {
                    offsets[it+1] = -ZenSlides.animateOffset(2, 1 + 0.25*it);
                    Zen.drawText((8-it) + " * factorial(" + (7-it) + ")", 260 + 78*it, offsets[it+1] + 250 + 65*it);
                }
                return ZenSlides.animationTimeout(4); // 4 seconds

            case 1:
                ZenSlides.setSubject("Code review");
                clearScreenAnimated(0); // Animated from West to East
                offsets[0] = ZenSlides.animateOffset();
                offsets[1] = ZenSlides.animateOffset(2, 1); // 2 second duration, 1 second delay
                offsets[2] = ZenSlides.animateOffset(2, 4); // 4 second delay
                offsets[3] = ZenSlides.animateOffset(2, 5);
                Zen.drawText("In mathematics, factorials can be represented with a piecewise function:", offsets[0]+20, 30);
                Zen.setFont(monospaceFont);
                Zen.drawText("=", offsets[1]+265, 80);
                ZenSlides.setColor(palette[0]);
                Zen.drawText("fact(n)", offsets[1]+170, 80);
                ZenSlides.setColor(palette[1]);
                Zen.drawText("1                 if n == 1,", offsets[1]+320, 70);
                ZenSlides.setColor(palette[2]);
                Zen.drawText("n * fact(n - 1)   if n > 1", offsets[1]+320, 90);
                Zen.setFont("DejaVu Sans Mono-40");
                ZenSlides.setColor(foregroundColor);
                Zen.drawText("{", offsets[1]+290, 84);
                Zen.setFont(defaultFont);
                Zen.drawText("Factorials can also be represented with just a few lines of recursive code:", offsets[2]+20, 130);
                Zen.setFont(monospaceFont);
                ZenSlides.setColor(palette[0]);
                Zen.drawText("public int factorial(int n) {", offsets[3]+190, 170);
                Zen.drawText("}", offsets[3]+190, 320);
                ZenSlides.setColor(palette[1]);
                Zen.drawText("if (n == 1)", offsets[3]+240, 200);
                Zen.drawText("return 1;", offsets[3]+285, 230);
                ZenSlides.setColor(palette[2]);
                Zen.drawText("else", offsets[3]+240, 260);
                Zen.drawText("return n * factorial(n - 1);", offsets[3]+285, 290);
                return ZenSlides.animationTimeout(7); // 7 seconds

            case 2:
                ZenSlides.setSubject("Code review");
                offsets[0] = -ZenSlides.animateOffset();
                offsets[1] = -ZenSlides.animateOffset(2, 0.5);
                offsets[2] = -ZenSlides.animateOffset(2, 1);

                Zen.drawText("There are two components that define recursion in these examples:", 20, offsets[0]+360);
                ZenSlides.setColor(palette[1]);
                Zen.drawText("Base case:", 20, offsets[1]+390);
                ZenSlides.setColor(palette[2]);
                Zen.drawText("Recursive call:", 20, offsets[2]+440);
                ZenSlides.setColor(foregroundColor);
                Zen.drawText("The base case defines when recursion ends. All recursive", 140, offsets[1]+390);
                Zen.drawText("functions have a base case, otherwise it would have infinite recursion.", 80, offsets[1]+410);
                Zen.drawText("The recursive call is one that calls the function again, but", 180, offsets[2]+440);
                Zen.drawText("with a changed argument that allows it to reach the base case", 80, offsets[2]+460);
                Zen.drawText("eventually. There are multiple types of recursive calls; the factorial", 80, offsets[2]+480);
                Zen.drawText("code above uses forward recursion (recursive result is manipulated", 80, offsets[2]+500);
                Zen.drawText("before being returned).", 80, offsets[2]+520);
                return ZenSlides.animationTimeout(3);

            case 3:
                ZenSlides.setSubject("A basic example");
                clearScreenAnimated(0);
                offsets[0] = ZenSlides.animateOffset();
                offsets[1] = ZenSlides.animateOffset(2, 0.5);
                offsets[2] = ZenSlides.animateOffset(2, 1);
                offsets[3] = ZenSlides.animateOffset(2, 1.5);
                Zen.drawText("Here's our (condensed) code for reference:", offsets[0]+20, 30);
                Zen.setFont(monospaceFont);
                Zen.drawText("factorial(1)", offsets[3]+20, 180);
                ZenSlides.setColor(palette[0]);
                Zen.drawText("int factorial(int n) {", offsets[1]+20, 60);
                ZenSlides.setColor(palette[1]);
                Zen.drawText("    if (n == 1) return 1;", offsets[1]+20, 80);
                ZenSlides.setColor(palette[2]);
                Zen.drawText("    else return n * factorial(n - 1); }", offsets[1]+20, 100);
                Zen.setFont(defaultFont);
                ZenSlides.setColor(foregroundColor);
                Zen.drawText("Let's start with a very basic example:", offsets[2]+20, 150);
                return ZenSlides.animationTimeout(4);

            case 4:
                ZenSlides.setSubject("A basic example");
                Zen.setFont(monospaceFont);
                ZenSlides.animateColor(1, 0, 2, backgroundColor, palette[1]);
                Zen.drawText("↓", 80, 200);
                Zen.drawText("1", 80, 220);
                Zen.drawText("← Stop recursing", 550, 80);
                return ZenSlides.animationTimeout(1);

            case 5:
                ZenSlides.setSubject("A basic example");
                offsets[0] = -ZenSlides.animateOffset();
                offsets[1] = -ZenSlides.animateOffset(2, 1);
                Zen.drawText("As expected, the base case is hit immediately, resulting in no recursion", 20, offsets[0]+340);
                Zen.drawText("at all. We can follow the same steps with the mathematical representation:", 20, offsets[0]+370);
                Zen.drawText("fact(1) == 1, because n == 1.", 20, offsets[0]+400);
                Zen.setFont(monospaceFont);
                Zen.drawText("=", 265, offsets[1]+480);
                ZenSlides.setColor(palette[0]);
                Zen.drawText("fact(n)", 170, offsets[1]+480);
                ZenSlides.setColor(palette[1]);
                Zen.drawText("1                 if n == 1,", 320, offsets[1]+470);
                ZenSlides.setColor(palette[2]);
                Zen.drawText("n * fact(n - 1)   if n > 1", 320, offsets[1]+490);
                Zen.setFont("DejaVu Sans Mono-40");
                ZenSlides.setColor(foregroundColor);
                Zen.drawText("{", 290, offsets[1]+484);
                return ZenSlides.animationTimeout(3);

            case 6:
                ZenSlides.setSubject("A more complete example");
                clearScreenAnimated(0);
                offsets[0] = ZenSlides.animateOffset();
                offsets[1] = ZenSlides.animateOffset(2, 1);
                offsets[2] = ZenSlides.animateOffset(2, 1.5);
                Zen.drawText("Here's a factorial call that actually recurses at least once:", offsets[1]+20, 120);
                Zen.setFont(monospaceFont);
                Zen.drawText("factorial(4)", offsets[2]+20, 150);
                ZenSlides.setColor(palette[0]);
                Zen.drawText("int factorial(int n) {", offsets[0]+20, 30);
                ZenSlides.setColor(palette[1]);
                Zen.drawText("    if (n == 1) return 1;", offsets[0]+20, 50);
                ZenSlides.setColor(palette[2]);
                Zen.drawText("    else return n * factorial(n - 1); }", offsets[0]+20, 70);
                return ZenSlides.animationTimeout(3.5);

            case 7:
                ZenSlides.setSubject("A more complete example");
                offsets[0] = ZenSlides.animateOffset(2, 1, 2, 235) + 235;
                offsets[1] = ZenSlides.animateOffset(2, 1, 2, 185) + 185;
                Zen.drawLine(213, 74, offsets[0]+213, 74);
                Zen.drawLine(20, 194, offsets[1]+20, 194);
                Zen.setFont(monospaceFont);
                ZenSlides.animateColor(1, 0, 2, backgroundColor, foregroundColor);
                Zen.drawText("4 *", 20, 190);
                ZenSlides.animateColor(1, 0, 2, backgroundColor, palette[2]);
                Zen.drawText("↓", 80, 170);
                Zen.drawText("factorial(3)", 65, 190);
                Zen.drawText("← Recurse", 550, 70);
                return ZenSlides.animationTimeout(3);

            case 8:
                ZenSlides.setSubject("A more complete example");
                offsets[0] = -ZenSlides.animateOffset();
                offsets[1] = -ZenSlides.animateOffset(2, 0.5);
                offsets[2] = -ZenSlides.animateOffset(2, 1);
                Zen.drawText("Now the recursive call case is hit. Following the code, the return value is the", 20, offsets[0]+270);
                Zen.drawText("value of \"n\" (4 in this case) multiplied by another call to itself with the", 20, offsets[0]+300);
                Zen.drawText("arguments of \"n - 1\" (3 in this case). Thus, the final result is:", 20, offsets[0]+330);
                Zen.drawText("However, we can't get the final result just yet! We still need to evaluate", 20, offsets[1]+420);
                Zen.drawText("that                              first in order to obtain its return value.", 20, offsets[1]+450);
                Zen.drawText("Let's see what happens if we let it recurse 2 more times...", 20, offsets[2]+510);
                Zen.setFont(monospaceFont);
                Zen.drawText("4 * factorial(3)", 20, offsets[0]+360);
                Zen.drawText("factorial(3)", 80, offsets[1]+450);
                return ZenSlides.animationTimeout(3);

            case 9:
                ZenSlides.setSubject("A more complete example");
                offsets[0] = ZenSlides.animateOffset(2, 0, 2, 350) + 350;
                offsets[1] = ZenSlides.animateOffset(2, 0, 2, 235) + 235;
                offsets[2] = ZenSlides.animateOffset(2, 0, 2, 185) + 185;
                ZenSlides.setColor(backgroundColor);
                Zen.drawLine(213, 74, offsets[1]+213, 74);
                Zen.drawLine(20, 194, offsets[2]+20, 194);
                Zen.fillRect(0, 240, Zen.getZenWidth(), offsets[0]);
                if (ZenSlides.getAnimationTime() >= 2) {
                    Zen.setFont(monospaceFont);
                    ZenSlides.animateColor(1, 2.5, 2, backgroundColor, foregroundColor);
                    Zen.drawText("3 *", 65, 230);
                    ZenSlides.animateColor(1, 2.5, 2, backgroundColor, palette[2]);
                    Zen.drawText("↓", 125, 210);
                    Zen.drawText("factorial(2)", 110, 230);
                    ZenSlides.animateColor(1, 4, 2, backgroundColor, foregroundColor);
                    Zen.drawText("2 *", 110, 270);
                    ZenSlides.animateColor(1, 4, 2, backgroundColor, palette[2]);
                    Zen.drawText("↓", 170, 250);
                    Zen.drawText("factorial(1)", 155, 270);
                }
                return ZenSlides.animationTimeout(5);

            case 10:
                ZenSlides.setSubject("A more complete example");
                offsets[0] = -ZenSlides.animateOffset();
                offsets[1] = -ZenSlides.animateOffset(2, 1);
                Zen.drawText("looks familiar, doesn't it? It's the base case! Recall what the", 180, offsets[1]+480);
                Zen.drawText("previous example looked like...", 20, offsets[1]+510);
                Zen.setFont(monospaceFont);
                Zen.drawText("factorial(1)", 20, offsets[1]+480);
                ZenSlides.setColor(secondaryBackgroundColor);
                Zen.fillRect(offsets[0]+520, 155, 165, 80);
                ZenSlides.setColor(foregroundColor);
                Zen.drawText("factorial(1)", offsets[0]+530, 180);
                ZenSlides.setColor(palette[1]);
                Zen.drawText("↓", offsets[0]+590, 200);
                Zen.drawText("1", offsets[0]+590, 220);
                return ZenSlides.animationTimeout(3);

            case 11:
                ZenSlides.setSubject("A more complete example");
                offsets[0] = ZenSlides.animateOffset(1, 0, 2, 150) + 150;
                ZenSlides.setColor(backgroundColor);
                Zen.fillRect(550, 50, offsets[0], 20);
                ZenSlides.animateColor(1, 1.5, 2, backgroundColor, palette[1]);
                Zen.setFont(monospaceFont);
                Zen.drawText("↓", 215, 290);
                Zen.drawText("1", 215, 310);
                Zen.drawText("← Stop recursing", 550, 50);
                return ZenSlides.animationTimeout(2.5);

            case 12:
                ZenSlides.setSubject("A more complete example");
                offsets[0] = ZenSlides.animateOffset(2, 0, 2, 160);
                offsets[1] = -ZenSlides.animateOffset(2, 1);
                offsets[2] = ZenSlides.animateOffset(1, 0, 2, 60);
                ZenSlides.setColor(backgroundColor);
                Zen.fillRect(0, offsets[2]+460, Zen.getZenWidth(), 60);
                ZenSlides.setColor(foregroundColor);

                Zen.drawText("Now that we know                            returns 1, we can replace it with 1.", 20, offsets[1]+480);
                Zen.drawText("This leaves us with:", 20, offsets[1]+510);
                Zen.setFont(monospaceFont);
                Zen.drawText("factorial(1)", 220, offsets[1]+480);
                Zen.drawText("2 * factorial(1) == 2 * 1 == 2", 230, offsets[1]+510);
                ZenSlides.setColor(backgroundColor);
                Zen.fillRect(offsets[0]+520, 155, 165, 80);
                return ZenSlides.animationTimeout(3);

            case 13:
                ZenSlides.setSubject("A more complete example");
                offsets[0] = ZenSlides.animateOffset(2, 0, 2, 160);
                offsets[1] = -ZenSlides.animateOffset(2, 0, 2, 40);
                offsets[2] = ZenSlides.animateOffset(1, 0, 2, 200) + 200;
                offsets[3] = -ZenSlides.animateOffset(2, 0, 2, 57);
                offsets[4] = -ZenSlides.animateOffset(2, 5, 2, 45);
                offsets[5] = -ZenSlides.animateOffset(2, 5, 2, 110);
                ZenSlides.setColor(backgroundColor);
                Zen.setFont(monospaceFont);
                Zen.fillRect(550, 30, offsets[2], 25);
                Zen.fillRect(155, offsets[1]+250, 200, 60);
                ZenSlides.animateColor(2, 0, 2, palette[1], foregroundColor);
                Zen.drawText("1", offsets[3]+158, offsets[1]+270);
                ZenSlides.animateColor(2, 0, 2, backgroundColor, foregroundColor);
                Zen.drawText("== 2", offsets[3]+183, offsets[1]+270);
                if (ZenSlides.getAnimationTime() >= 5) {
                    ZenSlides.setColor(backgroundColor);
                    Zen.fillRect(offsets[5]+100, 250, 200, 20);
                    ZenSlides.setColor(foregroundColor);
                    Zen.drawText("2", offsets[4]+170, 270);
                }
                return ZenSlides.animationTimeout(7);

            case 14:
                ZenSlides.setSubject("A more complete example");
                offsets[0] = ZenSlides.animateOffset(1, 0, 2, 60);
                offsets[1] = -ZenSlides.animateOffset(2, 1);
                ZenSlides.setColor(backgroundColor);
                Zen.fillRect(0, offsets[0]+460, Zen.getZenWidth(), 60);
                ZenSlides.setColor(foregroundColor);
                Zen.drawText("This process can be applied to the rest of the recursive calls.", 20, offsets[1]+480);
                Zen.drawText("The next step is to replace                             with 2.", 20, offsets[1]+510);
                Zen.setFont(monospaceFont);
                Zen.drawText("factorial(2)", 300, offsets[1]+510);
                Zen.drawText("", 20, offsets[1]+300);
                Zen.drawText("", 20, offsets[1]+300);

                return ZenSlides.animationTimeout(4);

            case 15:
                ZenSlides.setSubject("A more complete example");
                offsets[0] = ZenSlides.animateOffset(2, 4, 2, 160);
                offsets[1] = -ZenSlides.animateOffset(2, 4, 2, 40);
                offsets[2] = -ZenSlides.animateOffset(2, 4, 2, 57);
                offsets[3] = -ZenSlides.animateOffset(2, 9, 2, 45);
                offsets[4] = -ZenSlides.animateOffset(2, 9, 2, 110);
                offsets[5] = ZenSlides.animateOffset(1, 0, 2, 60);
                offsets[6] = -ZenSlides.animateOffset(2, 1);
                ZenSlides.setColor(backgroundColor);
                Zen.setFont(monospaceFont);
                Zen.fillRect(0, offsets[5]+460, Zen.getZenWidth(), 60);
                Zen.fillRect(110, offsets[1]+210, 200, 60);
                ZenSlides.setColor(foregroundColor);
                Zen.drawText("factorial(2) → 2                 3 * factorial(2) == 3 * 2 == 6", 20, offsets[6]+480);
                Zen.drawText("2", offsets[2]+113, offsets[1]+230);
                ZenSlides.animateColor(2, 4, 2, backgroundColor, foregroundColor);
                Zen.drawText("== 6", offsets[2]+138, offsets[1]+230);
                if (ZenSlides.getAnimationTime() >= 9) {
                    ZenSlides.setColor(backgroundColor);
                    Zen.fillRect(offsets[4]+55, 210, 200, 20);
                    ZenSlides.setColor(foregroundColor);
                    Zen.drawText("6", offsets[3]+125, 230);
                }
                return ZenSlides.animationTimeout(11);

            case 16:
                ZenSlides.setSubject("A more complete example");
                offsets[0] = ZenSlides.animateOffset(2, 3, 2, 160);
                offsets[1] = -ZenSlides.animateOffset(2, 3, 2, 40);
                offsets[2] = -ZenSlides.animateOffset(2, 3, 2, 57);
                offsets[3] = -ZenSlides.animateOffset(2, 8, 2, 50);
                offsets[4] = -ZenSlides.animateOffset(2, 8, 2, 110);
                offsets[5] = -ZenSlides.animateOffset();
                ZenSlides.setColor(backgroundColor);
                Zen.setFont(monospaceFont);
                Zen.fillRect(65, offsets[1]+170, 200, 60);
                ZenSlides.setColor(foregroundColor);
                Zen.drawText("factorial(3) → 6                 4 * factorial(3) == 4 * 6 == 24", 20, offsets[5]+510);
                Zen.drawText("6", offsets[2]+68, offsets[1]+190);
                ZenSlides.animateColor(2, 3, 2, backgroundColor, foregroundColor);
                Zen.drawText("== 24", offsets[2]+93, offsets[1]+190);
                if (ZenSlides.getAnimationTime() >= 8) {
                    ZenSlides.setColor(backgroundColor);
                    Zen.fillRect(offsets[4]+10, 170, 200, 20);
                    ZenSlides.setColor(foregroundColor);
                    Zen.drawText("24", offsets[3]+75, 190);
                    ZenSlides.animateColor(2, 8, 2, palette[2], foregroundColor);
                    Zen.drawText("↓", 80, 170);
                }
                return ZenSlides.animationTimeout(10);

            case 17:
                ZenSlides.setSubject("A more complete example");
                offsets[0] = ZenSlides.animateOffset(1, 0, 2, 60);
                offsets[1] = -ZenSlides.animateOffset(2, 1);
                ZenSlides.setColor(backgroundColor);
                Zen.fillRect(0, offsets[0]+460, Zen.getZenWidth(), 60);
                ZenSlides.setColor(foregroundColor);
                Zen.drawText("We arrive at the correct final result of:", 20, offsets[1]+510);
                Zen.setFont(monospaceFont);
                Zen.drawText("factorial(4) == 24", 415, offsets[1]+510);
                return ZenSlides.animationTimeout(3);

            case 18:
                ZenSlides.setSubject("Recap");
                clearScreenAnimated(0);
                offsets[0] = ZenSlides.animateOffset();
                offsets[1] = ZenSlides.animateOffset(2, 1);
                offsets[2] = -ZenSlides.animateOffset(2, 2);
                offsets[3] = -ZenSlides.animateOffset(2, 2.5);
                offsets[4] = -ZenSlides.animateOffset(2, 3);
                Zen.drawText("Let's go over a few key points.", offsets[0]+20, 30);
                Zen.drawText("The base case usually significies the end of recursion. It may be thought of", 20, offsets[2]+160);
                Zen.drawText("as a representation of how \"deep\" the recursion can go before it can no", 20, offsets[2]+190);
                Zen.drawText("longer recurse. Note that in all uses of recursion, the base case never has a", 20, offsets[2]+220);
                Zen.drawText("recursive call in its return value.", 20, offsets[2]+250);
                Zen.drawText("The recursive call is defined by having a call to itself in its return value. The", 20, offsets[3]+290);
                Zen.drawText("goal of the recursive calls is to eventually hit the base case. In the factorial", 20, offsets[3]+320);
                Zen.drawText("code above, this is guaranteed for any \"n > 1\", as the value of \"n\" will be", 20, offsets[3]+350);
                Zen.drawText("reduced to 1.", 20, offsets[3]+380);
                Zen.drawText("In addition to hitting the base case, forward recursive calls also usually", 20, offsets[4]+420);
                Zen.drawText("manipulate the return value of the recursive function. In the factorial code", 20, offsets[4]+450);
                Zen.drawText("above, this is represented by the multiplication of \"n\" by the return value of", 20, offsets[4]+480);
                Zen.drawText("the recursive call.", 20, offsets[4]+510);
                Zen.setFont(monospaceFont);
                ZenSlides.setColor(palette[0]);
                Zen.drawText("int factorial(int n) {", offsets[1]+20, 75);
                ZenSlides.setColor(palette[1]);
                Zen.drawText("    if (n == 1) return 1;                        Base case", offsets[1]+20, 95);
                ZenSlides.setColor(palette[2]);
                Zen.drawText("    else return n * factorial(n - 1); }          Recursive call", offsets[1]+20, 115);

                return ZenSlides.animationTimeout(5.5);

            default:
                System.out.println("Slide " + slideNumber + " does not exist.");
        }
        return true;
    }
}
