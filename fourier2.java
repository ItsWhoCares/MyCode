import java.math.*;
import java.util.*;

class fourier2{
    static double pi = 3.141592, sumx = 0, sumy = 0;
    static double sumycosnx[] = new double[20], sumysinnx[] = new double[20];
    static double a[] = new double[4], b[] = new double[4], ycosnx[][] = new double[4][20], ysinnx[][] = new double[4][20];
    public static void main(String args[]){
        int N;


        //Input number of terms
        System.out.print("Enter N : ");
        Scanner input = new Scanner(System.in);
        N = input.nextInt();

        //Input x and f(x) values
        double x[] = new double[N];
        double fx[] = new double[N];
        System.out.print("Enter " + N + " values of x : ");
        for(int i = 0; i < N; i++){
            x[i] = input.nextDouble();
            sumx += x[i];
        }

        System.out.print("Enetr " + N + " values of f(x) : ");
        for(int i = 0; i < N; i++){
            fx[i] = input.nextDouble();
            sumy += fx[i];
        }


        // //manual input for testing only
        // for(int i = 1; i < N; i++){
        //     x[i] = i;sumx += x[i];
        //     fx[i] = i*10;sumy += fx[i];
        // }

        //if x = 360 degree is given skip it
        if(x[N - 1] == 360){
            sumy -= fx[N-1];
            sumx -= x[N-1];
            N--;
        }

        // //Number of harmoics
        // System.out.print("Enter number of harmonics : ");
        // int terms = input.nextInt();
        int terms = 2;

        

        cosine(fx, x, N, terms);
        constants(N, terms);

        table(terms, N, x, fx);

    }

    public static void cosine(double[] y,double[] x, int N, int terms){

        //calculate y*cosx, y*cos2x, y*cos3x, .....
        //and sum of all terms of y*cosx, y*cos2x, y*cos3x, .....
        for(int i = 1; i <= terms; i++){
            sumycosnx[i] = 0;
            for(int j = 0; j < N; j++){
                ycosnx[i][j] = y[j] * Math.cos(i * Math.toRadians(x[j]));
                sumycosnx[i] += ycosnx[i][j];
            }
        }

        //calculate y*sinx, y*sin2x, y*sin3x, .....
        //and sum of all terms of y*sinx, y*sin2x, y*sin3x, .....
        for(int i = 1; i <= terms; i++){
            sumysinnx[i] = 0;
            for(int j = 0; j < N; j++){
                ysinnx[i][j] = y[j] * Math.sin(i * Math.toRadians(x[j]));
                sumysinnx[i] += ysinnx[i][j];
            }
        }
    }

    //calculates a0, a1, a2, .....
    //and b1, b2, b3, .....
    public static void constants(int N, int terms){
        a[0] = ((2 * sumy)/N);
        for(int i = 1; i <= terms; i++){
            a[i] = ((2 * sumycosnx[i])/N);
            b[i] = ((2 * sumysinnx[i])/N);
        }
    }

    //print the table
    public static void table(int terms, int rows, double[] x, double[] y){
        int n = 63;
        for(int i = 0; i < n; i++)
        System.out.print("-");
        System.out.print("\n");
        System.out.println("|    x    |    y    |  ycosx  |  ysinx  |  ycos2x  |  ysin2x  |");
        
        for(int i = 0; i < n; i++)
        System.out.print("-");

        System.out.print("\n");
        for(int i = 0; i < rows; i++)
        System.out.format("| %7.2f | %7.2f | %7.3f | %7.3f | %8.3f | %8.3f |\n",x[i],y[i],ycosnx[1][i],ysinnx[1][i],ycosnx[2][i],ysinnx[2][i]);
        

        for(int i = 0; i < n; i++)
        System.out.print("-");

        System.out.format("\n|   \u03A3 x   |   \u03A3 y   | \u03A3 ycosx | \u03A3 ysinx | \u03A3 ycos2x | \u03A3 ysin2x |");
        System.out.format("\n| %7.2f | %7.3f | %7.3f | %7.3f | %8.3f | %8.3f |\n", sumx,sumy,sumycosnx[1],sumysinnx[1],sumycosnx[2],sumysinnx[2]);

        for(int i = 0; i < n; i++)
        System.out.print("-");
        System.out.print("\n\n");


        System.out.println("The fourier series is given by : ");
        //prints the series with upto 3 decimal precision
        System.out.print("f(x) = ");
        System.out.printf("%.3f", a[0]/2);
        for(int i = 1; i <= terms; i++){
            System.out.printf(" + ((%.3f)cosx + (%.3f)sinx)", a[i], b[i]);
        }
        System.out.print("\n\n");
    }
    
}
