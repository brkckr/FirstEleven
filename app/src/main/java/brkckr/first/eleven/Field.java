package brkckr.first.eleven;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class Field extends View
{
    /**
     * paints
     */
    private Paint paintDarkerGrass;
    private Paint paintGrass;
    private Paint paintLine;
    private Paint paintPoint;

    /**
     * path
     */
    private Path touchPath;

    /**
     * dimensions
     */
    private float widthRatio = 50;
    private float heightRatio = 110;
    private int height;
    private int width;

    private float grassRatio = (float) 5.5;
    private float grassHeight;

    private float cornerCircleRatio = 2;
    private float cornerCircleRadius;

    private float sixYardBoxWidthRatio = 20;
    private float sixYardBoxHeightRatio = (float) 5.5;
    private float sixYardBoxWidth;
    private float sixYardBoxHeight;

    private float penaltyPointHeightRatio = 11;
    private float penaltyPointRatio = (float) 0.5;
    private float penaltyPointRadius;

    private float penaltyAreaWidthRatio = 35;
    private float penaltyAreaHeightRatio = (float) 16.5;
    private float penaltyAreaWidth;
    private float penaltyAreaHeight;

    private float penaltyArcWidthRatio = 10;
    private float penaltyArcWidth;

    private float centerCircleRatio = (float) 9.15;
    private float centerSpotPointRatio = (float) 0.5;
    private float centerCircleRadius;
    private float centerPointRadius;

    /**
     * coordinates
     */
    private float[] cornerSpot1CoordinateArray;
    private float[] cornerSpot2CoordinateArray;
    private float[] cornerSpot3CoordinateArray;
    private float[] cornerSpot4CoordinateArray;

    private float[] centerCircleCoordinateArray;

    private float[] penaltyPoint1CoordinateArray;
    private float[] penaltyPoint2CoordinateArray;

    private boolean isMeasured;

    public Field(Context context)
    {
        super(context);
        init();
    }

    public Field(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    /**
     * initializes style values and objects
     */
    private void init()
    {
        paintPoint = new Paint();
        paintPoint.setAntiAlias(true);
        paintPoint.setColor(getResources().getColor(android.R.color.white));
        paintPoint.setStyle(Paint.Style.FILL);

        paintDarkerGrass = new Paint();
        paintDarkerGrass.setAntiAlias(true);
        paintDarkerGrass.setColor(getResources().getColor(R.color.colorFieldDarker));
        paintDarkerGrass.setStyle(Paint.Style.FILL);

        paintGrass = new Paint();
        paintGrass.setAntiAlias(true);
        paintGrass.setColor(getResources().getColor(R.color.colorField));
        paintGrass.setStyle(Paint.Style.FILL);

        paintLine = new Paint();
        paintLine.setAntiAlias(true);
        paintLine.setStrokeWidth(5);
        paintLine.setColor(getResources().getColor(android.R.color.white));
        paintLine.setStyle(Paint.Style.STROKE);

        touchPath = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        if (!isMeasured)
        {
            isMeasured = true;

            width = MeasureSpec.getSize(widthMeasureSpec);
            height = MeasureSpec.getSize(heightMeasureSpec);

            grassHeight = (height / heightRatio * grassRatio);

            cornerCircleRadius = (width / widthRatio * cornerCircleRatio);
            calculateCornerSpotCoordinates();

            centerCircleRadius = (width / widthRatio * centerCircleRatio);
            centerPointRadius = (width / widthRatio * centerSpotPointRatio);
            calculateCenterCircleCoordinates();

            sixYardBoxHeight = (height / heightRatio * sixYardBoxHeightRatio);
            sixYardBoxWidth = (width / widthRatio * sixYardBoxWidthRatio);

            penaltyAreaHeight = (height / heightRatio * penaltyAreaHeightRatio);
            penaltyAreaWidth = (width / widthRatio * penaltyAreaWidthRatio);
            penaltyPointRadius = (width / widthRatio * penaltyPointRatio);
            calculatePenaltyPointCoordinates();

            penaltyArcWidth = (width / widthRatio * penaltyArcWidthRatio);
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        drawGrass(canvas);
        drawFieldLines(canvas);

        drawCornerSpots(canvas);

        drawCenterLine(canvas);
        drawCenterCircle(canvas);
        drawCenterPoint(canvas);

        drawPenalty1Point(canvas);
        drawPenalty2Point(canvas);

        drawSixYard1Box(canvas);
        drawSixYard2Box(canvas);

        drawPenalty1Area(canvas);
        drawPenalty2Area(canvas);

        drawPenaltyArea1Arc(canvas);
        drawPenaltyArea2Arc(canvas);

        drawTouch(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {

        switch (event.getAction())
        {

            case MotionEvent.ACTION_DOWN:
                touchPath.moveTo(event.getX(), event.getY());
                break;

            case MotionEvent.ACTION_MOVE:
                touchPath.lineTo(event.getX(), event.getY());
                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                break;
        }

        return true;
    }

    @Override
    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();

        paintPoint = null;
    }

    private void drawGrass(Canvas canvas)
    {
        float top = 0;
        float bottom = grassHeight;

        for (int i = 0; i < 20; i++)
        {
            top = grassHeight * i;
            bottom += grassHeight;

            if (i == 19)
            {
                bottom = height;
            }

            if (i % 2 == 0)
            {
                canvas.drawRect(0, top, width, bottom, paintDarkerGrass);
            } else
            {
                canvas.drawRect(0, top, width, bottom, paintGrass);
            }
        }
    }

    private void drawFieldLines(Canvas canvas)
    {
        Path path = new Path();
        path.addRoundRect(new RectF(0, 0, width, height), 0, 0, Path.Direction.CCW);
        canvas.clipPath(path, Region.Op.INTERSECT);

        canvas.drawRect(0, 0, width, height, paintLine);
    }

    private void drawCornerSpots(Canvas canvas)
    {
        canvas.drawCircle(cornerSpot1CoordinateArray[0], cornerSpot1CoordinateArray[1], cornerCircleRadius, paintLine);
        canvas.drawCircle(cornerSpot2CoordinateArray[0], cornerSpot2CoordinateArray[1], cornerCircleRadius, paintLine);
        canvas.drawCircle(cornerSpot3CoordinateArray[0], cornerSpot3CoordinateArray[1], cornerCircleRadius, paintLine);
        canvas.drawCircle(cornerSpot4CoordinateArray[0], cornerSpot4CoordinateArray[1], cornerCircleRadius, paintLine);
    }

    private void drawCenterLine(Canvas canvas)
    {
        canvas.drawLine(0, height / 2, width, height / 2, paintLine);
    }

    private void drawCenterCircle(Canvas canvas)
    {
        canvas.drawCircle(centerCircleCoordinateArray[0], centerCircleCoordinateArray[1], centerCircleRadius, paintLine);
    }

    private void drawCenterPoint(Canvas canvas)
    {
        canvas.drawCircle(centerCircleCoordinateArray[0], centerCircleCoordinateArray[1], centerPointRadius, paintPoint);
    }

    private void drawPenalty1Point(Canvas canvas)
    {
        canvas.drawCircle(penaltyPoint1CoordinateArray[0], penaltyPoint1CoordinateArray[1], centerPointRadius, paintPoint);
    }

    private void drawPenalty2Point(Canvas canvas)
    {
        canvas.drawCircle(penaltyPoint2CoordinateArray[0], penaltyPoint2CoordinateArray[1], centerPointRadius, paintPoint);
    }

    private void drawSixYard1Box(Canvas canvas)
    {
        canvas.drawRect(((width - sixYardBoxWidth) / 2), 0, width - ((width - sixYardBoxWidth) / 2), sixYardBoxHeight, paintLine);
    }

    private void drawSixYard2Box(Canvas canvas)
    {
        canvas.drawRect(((width - sixYardBoxWidth) / 2), height - sixYardBoxHeight, width - ((width - sixYardBoxWidth) / 2), height, paintLine);
    }

    private void drawPenalty1Area(Canvas canvas)
    {
        canvas.drawRect(((width - penaltyAreaWidth) / 2), 0, width - ((width - penaltyAreaWidth) / 2), penaltyAreaHeight, paintLine);
    }

    private void drawPenalty2Area(Canvas canvas)
    {
        canvas.drawRect(0 + ((width - penaltyAreaWidth) / 2), height - penaltyAreaHeight, width - ((width - penaltyAreaWidth) / 2), height, paintLine);
    }

    private void drawPenaltyArea1Arc(Canvas canvas)
    {
        float centerX = width / 2;
        float centerY = penaltyAreaHeight;
        float radius = penaltyArcWidth / 2;

        RectF rectF = new RectF(centerX - radius, centerY - radius, centerX + radius, centerY + radius);

        canvas.drawArc(rectF, 0, 180, false, paintLine);
    }

    private void drawPenaltyArea2Arc(Canvas canvas)
    {
        float centerX = width / 2;
        float centerY = height - penaltyAreaHeight;
        float radius = penaltyArcWidth / 2;

        RectF rectF = new RectF(centerX - radius, centerY - radius, centerX + radius, centerY + radius);

        canvas.drawArc(rectF, 180, 180, false, paintLine);
    }

    private void drawTouch(Canvas canvas)
    {
        canvas.drawPath(touchPath, paintLine);
    }

    private void calculateCornerSpotCoordinates()
    {
        cornerSpot1CoordinateArray = new float[2];
        cornerSpot1CoordinateArray[0] = 0;
        cornerSpot1CoordinateArray[1] = 0;

        cornerSpot2CoordinateArray = new float[2];
        cornerSpot2CoordinateArray[0] = width;
        cornerSpot2CoordinateArray[1] = 0;

        cornerSpot3CoordinateArray = new float[2];
        cornerSpot3CoordinateArray[0] = 0;
        cornerSpot3CoordinateArray[1] = height;

        cornerSpot4CoordinateArray = new float[2];
        cornerSpot4CoordinateArray[0] = width;
        cornerSpot4CoordinateArray[1] = height;
    }

    private void calculateCenterCircleCoordinates()
    {
        centerCircleCoordinateArray = new float[2];
        centerCircleCoordinateArray[0] = width / 2;
        centerCircleCoordinateArray[1] = height / 2;
    }

    private void calculatePenaltyPointCoordinates()
    {
        penaltyPoint1CoordinateArray = new float[2];
        penaltyPoint1CoordinateArray[0] = width / 2;
        penaltyPoint1CoordinateArray[1] = (height / heightRatio * penaltyPointHeightRatio);

        penaltyPoint2CoordinateArray = new float[2];
        penaltyPoint2CoordinateArray[0] = width / 2;
        penaltyPoint2CoordinateArray[1] = height - (height / heightRatio * penaltyPointHeightRatio);
    }
}
