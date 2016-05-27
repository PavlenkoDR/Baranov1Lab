import java.awt.Color;
import java.awt.Graphics;


public class Paint {
	static double BorderMinX = -20;
	static double BorderMaxX = 20;
	static double BorderMinY = -20;
	static double BorderMaxY = 20;
    static double ceil = 20;
	
    double tmp1;
    double tmp2;
    double xGraphPoly[];
    double yGraphPoly[];
    double xGraphApprox[];
    double yGraphApprox[];
    double xScale1 = 16;
    double yScale1 = 16;
    double PosX = 0;
    double PosY = 0;
    double ActionRadius = 0.5;
    
    int width;
    int height;
    
    static int PointsRadius = 3;
    static int ActionPoint = -1;

    Function func;
    
	Paint(int _width, int _height)
	{
  		width = _width;
  		height = _height;

		xGraphPoly = new double[(int)Math.round((BorderMaxX - BorderMinX)*ceil) + 1];
		yGraphPoly = new double[(int)Math.round((BorderMaxX - BorderMinX)*ceil) + 1];
		xGraphApprox = new double[(int)Math.round((BorderMaxX - BorderMinX)*ceil) + 1];
		yGraphApprox = new double[(int)Math.round((BorderMaxX - BorderMinX)*ceil) + 1];
        //g.setColor(Color.BLACK);
	}
	void SetFunc(Function _func)
	{
		func = _func;
		if (func.GetPointsFlag)
        for (double i = BorderMinX; i <= BorderMaxX; i+= 1/(double)ceil)
        {
        	xGraphPoly[(int)Math.round((i - BorderMinX)*ceil)] = i;
        	yGraphPoly[(int)Math.round((i - BorderMinX)*ceil)] = -(func.interp.getValue(i));
	  		xGraphApprox[(int)Math.round((i - BorderMinX)*ceil)] = i;
	  		yGraphApprox[(int)Math.round((i - BorderMinX)*ceil)] = -(func.approx.getValue(i, func.nApprox));
        }
	}

	void DrawGraphPoly(Graphics g)
	{
        for (double i = BorderMinX; i <= BorderMaxX - 1/(double)ceil; i+= 1/(double)ceil)
        {
        	//g.setColor(Color.BLACK);
        	double x1 = xGraphPoly[(int)Math.round((i - BorderMinX)*ceil)]*xScale1 + PosX;
        	double y1 = yGraphPoly[(int)Math.round((i - BorderMinX)*ceil)]*yScale1 + PosY;
        	double x2 = xGraphPoly[(int)Math.round((i - BorderMinX)*ceil)+1]*xScale1 + PosX;
        	double y2 = yGraphPoly[(int)Math.round((i - BorderMinX)*ceil)+1]*yScale1 + PosY;
	  		  if (y1 < 0) y1 = -2;
	  		  else if (y1 > height) y1 = height + 2;
	  		  if (y2 < 0) y2 = -2;
	  		  else if (y2 > height) y2 = height + 2;
      	  	g.drawLine(
      			  (int)Math.round(x1), 
      			  (int)Math.round(y1), 
      			  (int)Math.round(x2), 
      			  (int)Math.round(y2)
      			  );
        }
	}
	
	void DrawGraphApprox(Graphics g)
	{
        for (double i = BorderMinX; i <= BorderMaxX - 1/(double)ceil; i+= 1/(double)ceil)
        {
        	//g.setColor(Color.BLACK);
        	double x1 = xGraphApprox[(int)Math.round((i - BorderMinX)*ceil)]*xScale1 + PosX;
        	double y1 = yGraphApprox[(int)Math.round((i - BorderMinX)*ceil)]*yScale1 + PosY;
        	double x2 = xGraphApprox[(int)Math.round((i - BorderMinX)*ceil)+1]*xScale1 + PosX;
        	double y2 = yGraphApprox[(int)Math.round((i - BorderMinX)*ceil)+1]*yScale1 + PosY;
	  		  if (y1 < 0) y1 = -2;
	  		  else if (y1 > height) y1 = height + 2;
	  		  if (y2 < 0) y2 = -2;
	  		  else if (y2 > height) y2 = height + 2;
      	  	g.drawLine(
      			  (int)Math.round(x1), 
      			  (int)Math.round(y1), 
      			  (int)Math.round(x2), 
      			  (int)Math.round(y2)
      			  );
        }
	}
	void DrawPoints(Graphics g)
	{
        for (int i = 0; i < func.size; i++)
        {
      	  	g.drawOval(
      	  			(int)Math.round(func.X[i]*xScale1 + PosX) - PointsRadius, 
      	  			(int)Math.round(-func.Y[i]*yScale1 + PosY) - PointsRadius, 
      	  		    PointsRadius*2, 
      	  		    PointsRadius*2);
        }
	}
	
	void DrawGrid(Graphics g)
	{
    	g.setColor(new Color(220, 220, 220));
		for (double i = (BorderMinX)*xScale1; i <= (BorderMaxX)*xScale1; i+= xScale1)
			for (double j = (BorderMinY)*yScale1; j <= (BorderMaxY)*yScale1; j+= yScale1)
			{
	      	  	g.drawLine(
	      	  			(int)Math.round(i + PosX), 
	      	  			(int)Math.round(BorderMinY*yScale1 + PosY), 
	      	  			(int)Math.round(i + PosX), 
	      	  			(int)Math.round(BorderMaxY*yScale1 + PosY)
	      			  );
	      	  	g.drawLine(
		      			(int)Math.round(BorderMinX*xScale1 + PosX), 
		      			(int)Math.round(j + PosY), 
		      			(int)Math.round(BorderMaxX*xScale1 + PosX), 
		      			(int)Math.round(j + PosY)
		      			  );
			}
	}
	void DrawGridHard(Graphics g)
	{
    	g.setColor(new Color(220, 220, 220));
    	double stepXLeft = BorderMinX;
    	double stepXRight = BorderMaxX;
    	double stepYLeft = BorderMinX;
    	double stepYRight = BorderMaxX;

    	while(stepXLeft*xScale1 + PosX > 0)
    		stepXLeft--;
    	while(stepXRight*xScale1 + PosX < width)
    		stepXRight++;
    	while(stepYLeft*yScale1 + PosY > 0)
    		stepYLeft--;
    	while(stepYRight*yScale1 + PosY < height)
    		stepYRight++;
    	
		for (double i = stepXLeft*xScale1; i <= stepXRight*xScale1; i+= xScale1)
			for (double j = stepYLeft*yScale1; j <= stepYRight*yScale1; j+= yScale1)
			{
	      	  	g.drawLine(
	      	  			(int)Math.round(i + PosX), 
	      	  			(int)Math.round(0), 
	      	  			(int)Math.round(i + PosX), 
	      	  			(int)Math.round(height)
	      			  );
	      	  	g.drawLine(
		      			(int)Math.round(0), 
		      			(int)Math.round(j + PosY), 
		      			(int)Math.round(width), 
		      			(int)Math.round(j + PosY)
		      			  );
			}
	}
	void DrawCoord(Graphics g)
	{
    	g.setColor(new Color(0, 0, 0));

  	  	g.drawLine(
  	  			(int)Math.round(PosX), 
  	  			(int)Math.round(BorderMinY*yScale1 + PosY), 
  	  			(int)Math.round(PosX), 
  	  			(int)Math.round(BorderMaxY*yScale1 + PosY)
  			  );
		g.drawString("y", 
				(int)Math.round(PosX + PointsRadius) - 20 , 
				((int)Math.round(BorderMinY*yScale1 + PosY + 10)>10)?((int)Math.round(BorderMinY*yScale1 + PosY + 10)):10);
  	  	g.drawLine(
      			(int)Math.round(BorderMinX*xScale1 + PosX), 
      			(int)Math.round(PosY), 
      			(int)Math.round(BorderMaxX*xScale1 + PosX), 
      			(int)Math.round(PosY)
      			  );
		g.drawString("X", 
				((int)Math.round(BorderMaxX*xScale1 + PosX)< 10/*width*/)?((int)Math.round(BorderMaxX*xScale1 + PosX) - 10):(10/*width*/ - 10), 
				(int)Math.round(PosY) - 10);
	}

}
