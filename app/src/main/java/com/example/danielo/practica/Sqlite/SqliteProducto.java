package com.example.danielo.practica.Sqlite;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.danielo.practica.R;

public class SqliteProducto extends AppCompatActivity {
    SQLiteDatabase db;
    Button bVolver;
    EditText eNombre, eTipo, eID, eMarca, ePrecio;
    TextView registro;
    String nombre,textReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_producto);
        Bundle b = getIntent().getExtras();
        nombre= b.getString("Nombre");
        db = cargarBD(nombre);
        eNombre = (EditText) findViewById(R.id.eNombre);
        eTipo = (EditText) findViewById(R.id.eTipo);
        eID = (EditText) findViewById(R.id.eID);
        eMarca = (EditText) findViewById(R.id.eMarca);
        ePrecio = (EditText) findViewById(R.id.ePrecio);
        registro = (TextView) findViewById(R.id.registro);

        if(db != null)
        {
            db.execSQL("INSERT INTO PRODUCTO VALUES (10001 ,'impresora' ,'canon','pixma mg2550S', 39.91  )");
            db.execSQL("INSERT INTO PRODUCTO VALUES (10002 ,'impresora', 'hp','desjet 2130',49.9 )");
            db.execSQL("INSERT INTO PRODUCTO VALUES (10003, 'portatil', 'asus','f540lj-xx439t',499.0 )");
            db.execSQL("INSERT INTO PRODUCTO VALUES (10004, 'portatil', 'msi', 'gp62mvr 6rf', 1299.0 )");
            db.execSQL("INSERT INTO PRODUCTO VALUES (10005, 'antivirus', 'kaspersky', '1 equipo 2017 base', 29.95)");
            db.close();
        }



    }

    public SQLiteDatabase cargarBD(String nombre) {
        //Abrimos la base de datos en modo escritura
        UsuariosSQLiteHelper usdbh =
                new UsuariosSQLiteHelper(this, nombre, null, 1);

        return usdbh.getWritableDatabase();
    }

    public void insert(View v) {
        db = cargarBD(nombre);

        if (!eID.getText().toString().equals("") && !eTipo.getText().toString().equals("")&& !eMarca.getText().toString().equals("")&& !eNombre.getText().toString().equals("")&& !ePrecio.getText().toString().equals("")) {
            ContentValues nuevoRegistro = new ContentValues();
            nuevoRegistro.put("ID", eID.getText().toString());
            nuevoRegistro.put("Tipo",eTipo.getText().toString());
            nuevoRegistro.put("Marca",eMarca.getText().toString());
            nuevoRegistro.put("Nombre",eNombre.getText().toString());
            nuevoRegistro.put("Precio",ePrecio.getText().toString());
            db.insert("PRODUCTO", null , nuevoRegistro);
            Toast t = Toast.makeText(this,"Insertado correctamente",Toast.LENGTH_SHORT);
            t.show();
        } else {
            Toast t = Toast.makeText(this,"Rellene los campos",Toast.LENGTH_SHORT);
            t.show();
        }

        db.close();
    }
    public void delete(View v) {
        db = cargarBD(nombre);

        if (!eID.getText().toString().equals("")) {
            String[] args = new String[]{eID.getText().toString()};
            db.execSQL("DELETE FROM PRODUCTO WHERE ID=?", args);
        }   else  if (!eTipo.getText().toString().equals("")) {
            String[] args = new String[]{eTipo.getText().toString()};
            db.execSQL("DELETE FROM PRODUCTO WHERE Tipo=?", args);
        }   else  if (!eMarca.getText().toString().equals("")) {
            String[] args = new String[]{eMarca.getText().toString()};
            db.execSQL("DELETE FROM PRODUCTO WHERE Marca=?", args);
        }   else  if (!eNombre.getText().toString().equals("")) {
            String[] args = new String[]{eNombre.getText().toString()};
            db.execSQL("DELETE FROM PRODUCTO WHERE Nombre=?", args);
        }   else  if (!ePrecio.getText().toString().equals("")) {
            String[] args = new String[]{ePrecio.getText().toString()};
            db.execSQL("DELETE FROM PRODUCTO WHERE Precio=?", args);
        }
        db.close();
        Toast t = Toast.makeText(this,"Campos eliminados",Toast.LENGTH_SHORT);
        t.show();
    }

    public void update(View v) {
        db = cargarBD(nombre);

        if (!eID.getText().toString().equals("") && !eTipo.getText().toString().equals("")&& !eMarca.getText().toString().equals("")&& !eNombre.getText().toString().equals("")&& !ePrecio.getText().toString().equals("")) {
            ContentValues valores = new ContentValues();
            valores.put("Tipo",eTipo.getText().toString());
            valores.put("Marca",eMarca.getText().toString());
            valores.put("Nombre",eNombre.getText().toString());
            valores.put("Precio",ePrecio.getText().toString());

            String[] args = new String[]{eID.getText().toString()};
            db.update("PRODUCTO", valores, "ID=?", args);
            Toast t = Toast.makeText(this,"Campos actualizados",Toast.LENGTH_SHORT);
            t.show();
        }else {
            Toast t = Toast.makeText(this,"No puede haber campos vacios",Toast.LENGTH_SHORT);
            t.show();
        }


        db.close();
    }


    public void query(View v) {
        db = cargarBD(nombre);
        textReg = "";
        String[] campos = new String[] {"ID", "Tipo", "Marca", "Nombre", "Precio"};
        String[] args;
        Cursor c;
        String nombreP,idP,tipoP,marcaP,precioP;

        if (!eNombre.getText().toString().equals("")) {

            args = new String[] {eNombre.getText().toString()};

            c = db.query("PRODUCTO", campos, "Nombre=?", args, null, null, null);

            textReg = "ID------TIPO-----MARCA----NOMBRE----PRECIO\n";


            if (c.moveToFirst()) {

                do {
                    idP = c.getString(0);
                    tipoP = c.getString(1);
                    marcaP = c.getString(2);
                    nombreP = c.getString(3);
                    precioP = c.getString(4);
                    textReg += idP + "-" + tipoP + "-" + marcaP +  "-" + nombreP +  "-" + precioP +  "\n";
                } while(c.moveToNext());
            }
        }
        if (!eNombre.getText().toString().equals("")) {

            args = new String[] {eNombre.getText().toString()};

            c = db.query("PRODUCTO", campos, "Nombre=?", args, null, null, null);

            textReg = "ID------TIPO-----MARCA----NOMBRE----PRECIO\n";

            if (c.moveToFirst()) {

                do {
                    idP = c.getString(0);
                    tipoP = c.getString(1);
                    marcaP = c.getString(2);
                    nombreP = c.getString(3);
                    precioP = c.getString(4);
                    textReg += idP + "-" + tipoP + "-" + marcaP +  "-" + nombreP +  "-" + precioP +  "\n";
                } while(c.moveToNext());
            }
        }
        if (!eID.getText().toString().equals("")) {

            args = new String[] {eID.getText().toString()};

            c = db.query("PRODUCTO", campos, "ID=?", args, null, null, null);

            textReg = "ID------TIPO-----MARCA----NOMBRE----PRECIO\n";


            if (c.moveToFirst()) {

                do {
                    idP = c.getString(0);
                    tipoP = c.getString(1);
                    marcaP = c.getString(2);
                    nombreP = c.getString(3);
                    precioP = c.getString(4);
                    textReg += idP + "-" + tipoP + "-" + marcaP +  "-" + nombreP +  "-" + precioP +  "\n";
                } while(c.moveToNext());
            }
        }
        if (!eTipo.getText().toString().equals("")) {

            args = new String[] {eTipo.getText().toString()};

            c = db.query("PRODUCTO", campos, "Tipo=?", args, null, null, null);

            textReg = "ID------TIPO-----MARCA----NOMBRE----PRECIO\n";


            if (c.moveToFirst()) {

                do {
                    idP = c.getString(0);
                    tipoP = c.getString(1);
                    marcaP = c.getString(2);
                    nombreP = c.getString(3);
                    precioP = c.getString(4);
                    textReg += idP + "-" + tipoP + "-" + marcaP +  "-" + nombreP +  "-" + precioP +  "\n";
                } while(c.moveToNext());
            }
        }
        if (!eMarca.getText().toString().equals("")) {

            args = new String[] {eMarca.getText().toString()};

            c = db.query("PRODUCTO", campos, "Marca=?", args, null, null, null);

            textReg = "ID------TIPO-----MARCA----NOMBRE----PRECIO\n";

            //Nos aseguramos de que existe al menos un registro
            if (c.moveToFirst()) {
                //Recorremos el cursor hasta que no haya más registros
                do {
                    idP = c.getString(0);
                    tipoP = c.getString(1);
                    marcaP = c.getString(2);
                    nombreP = c.getString(3);
                    precioP = c.getString(4);
                    textReg += idP + "-" + tipoP + "-" + marcaP +  "-" + nombreP +  "-" + precioP +  "\n";
                } while(c.moveToNext());
            }
        }
        if (!eNombre.getText().toString().equals("")) {

            args = new String[] {eNombre.getText().toString()};

            c = db.query("PRODUCTO", campos, "Nombre=?", args, null, null, null);

            textReg = "ID------TIPO-----MARCA----NOMBRE----PRECIO\n";

            //Nos aseguramos de que existe al menos un registro
            if (c.moveToFirst()) {
                //Recorremos el cursor hasta que no haya más registros
                do {
                    idP = c.getString(0);
                    tipoP = c.getString(1);
                    marcaP = c.getString(2);
                    nombreP = c.getString(3);
                    precioP = c.getString(4);
                    textReg += idP + "-" + tipoP + "-" + marcaP +  "-" + nombreP +  "-" + precioP +  "\n";
                } while(c.moveToNext());
            }
        }
        if (!ePrecio.getText().toString().equals("")) {

            args = new String[] {ePrecio.getText().toString()};

            c = db.query("PRODUCTO", campos, "Precio=?", args, null, null, null);

            textReg = "ID------TIPO-----MARCA----NOMBRE----PRECIO\n";

            //Nos aseguramos de que existe al menos un registro
            if (c.moveToFirst()) {


                    idP = c.getString(0);
                    tipoP = c.getString(1);
                    marcaP = c.getString(2);
                    nombreP = c.getString(3);
                    precioP = c.getString(4);
                    textReg += idP + "-" + tipoP + "-" + marcaP +  "-" + nombreP +  "-" + precioP +  "\n";

            }
        }
        if (eID.getText().toString().equals("") && eTipo.getText().toString().equals("") && eMarca.getText().toString().equals("") && eNombre.getText().toString().equals("") && ePrecio.getText().toString().equals("")) {

            args = new String[] {ePrecio.getText().toString()};

            c = db.rawQuery("SELECT * FROM PRODUCTO",null);

            textReg = "ID------TIPO-----MARCA----NOMBRE----PRECIO\n";

            //Nos aseguramos de que existe al menos un registro
            if (c.moveToFirst()) {
                //Recorremos el cursor hasta que no haya más registros
                do {
                    idP = c.getString(0);
                    tipoP = c.getString(1);
                    marcaP = c.getString(2);
                    nombreP = c.getString(3);
                    precioP = c.getString(4);
                    textReg += idP + "-" + tipoP + "-" + marcaP +  "-" + nombreP +  "-" + precioP +  "\n";
                } while(c.moveToNext());
            }
        }

        textReg += "\n";


        registro.setText(textReg);
        db.close();
    }

    public void volver (View view){

        Intent intent = new Intent(SqliteProducto.this , SqlitePrincipal.class);
        startActivity(intent);
    }

}
