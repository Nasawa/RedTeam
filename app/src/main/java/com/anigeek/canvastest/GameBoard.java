package com.anigeek.canvastest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class GameBoard extends View
{
	private Paint p;
	private Path path;
	private Point point;
	private int w = 0, h = 0, alpha = 210, fade = 1;

	public GameBoard(Context context, AttributeSet aSet)
	{
		super(context, aSet);
		//it's best not to create any new objects in the on draw
		//initialize them as class variables here
		p = new Paint();
		path = new Path();
		point = new Point(20, 20);
	}

	public void definePath(Canvas canvas)
	{
		w = canvas.getWidth();
		h = canvas.getHeight();
		Path.Direction d = Path.Direction.CW;
		path.addRect(0, h * .66f, w * .45f, h, d);

		path.addRect(w * .55f, h * .8f, w, h, d);

		path.addRect(0, h * .3f, w * .3f, h * .6f, d);

		path.addRect(w * .59f, h * .54f, w, h * .72f, d);
		path.addRect(w * .4f, h * .3f, w, h * .55f, d);
		path.addRect(w * .82f, h * .15f, w, h * .4f, d);

		path.addRect(0, 0, w * .70f, h * .20f, d);
		path.addRect(0, 0, w, h * .08f, d);
	}


	@Override
	synchronized public void onDraw(Canvas canvas)
	{
		if(w == 0 && h == 0)
			definePath(canvas);
		//create a black canvas
		p.setStyle(Paint.Style.FILL_AND_STROKE);
		p.setColor(Color.rgb(40, 10, 200));
		p.setAlpha(alpha += fade);
		if(alpha >= 252 || alpha <= 210)
			fade *= -1;
		p.setStrokeWidth(1);
		canvas.drawRect(0, 0, getWidth(), getHeight(), p);

		p.setColor(Color.GRAY);
		p.setAlpha(255);
		p.setStrokeWidth(10);
		canvas.drawPath(path, p);
		canvas.drawPoint(point.x, point.y, p);

	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		return false;
	}
}