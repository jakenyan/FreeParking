package com.example.stef.freeparking;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;


public class Login extends ActionBarActivity {

    static Spot spots[];

    String data = "";
    String first = "";
    String last = "";
    String user = "";
    String email = "";

    public static void sqlMain ( String query )
    {

        String url = "jdbc:mysql://maxwell.sju.edu:3306/";
        Connection   con;
        Statement    stmt;
        ResultSet    rs;
        String db = "stefandb";

        try
        {
            // append the dbname to the URL
            url = url + db;

            // Load the jdbc-odbc bridge driver

            Class.forName("com.mysql.jdbc.Driver").newInstance();

            // Attempt to connect to a driver.
            con = DriverManager.getConnection(url, "stefan", "sp2015android");

            //  Create a Statement object so we can submit
            //  SQL statements to the driver
            stmt = con.createStatement();

            // Submit a query, creating a ResultSet object
            rs = stmt.executeQuery(query);
            iterateResult(rs);

            stmt.close();
            con.close();
        }
        catch (Exception e)
        {e.printStackTrace();}
    }

    public void dbCall(View view) {
        new Thread(new Runnable(){
            public void run(){
                sqlMain("select * from mandevilleA where vacant = 1;");
            }
        }).start();

    }


    public void toMaps(View view) {
        Intent intent = new Intent(Login.this, mapsPage.class);
        startActivity(intent);
    }


    //***


    private static void iterateResult(ResultSet rs)
            throws SQLException
    {
        int count = 0;
        int numCols = rs.getMetaData().getColumnCount();
        while (rs.next())
        {
            for (int i=1; i<=numCols; i++){
                if(rs.getMetaData().getColumnName(i).equals("latitude"))
                    spots[count].latitude = rs.getDouble(i);
                if (rs.getMetaData().getColumnName(i).equals("longitude"))
                    spots[count].longitude = rs.getDouble(i);
            }
            count++;
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        spots = new Spot[10];
        for(int i = 0; i < 10; i++){
            spots[i] = new Spot();
        }
        new Thread(new Runnable(){
            public void run(){
                sqlMain("select * from mandevilleA where vacant = 1;");
            }
        }).start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
