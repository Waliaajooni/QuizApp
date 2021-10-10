package com.example.quizapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

import com.example.quizapp.Question;

import java.util.ArrayList;
import java.util.List;


public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "triviaQuiz";
    // tasks table name

    private SQLiteDatabase dbase;
    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase=db;
        String sql = "CREATE TABLE IF NOT EXISTS " + QuizContract.MovieEntry.TABLE_QUEST + " ( "
                + QuizContract.MovieEntry.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + QuizContract.MovieEntry.KEY_QUES
                + " TEXT, " + QuizContract.MovieEntry.COUNTER + " TEXT, " + QuizContract.MovieEntry.KEY_ANSWER+ " TEXT, "+QuizContract.MovieEntry.KEY_OPTA +" TEXT, "
                +QuizContract.MovieEntry.KEY_OPTB +" TEXT, "+QuizContract.MovieEntry.KEY_OPTC+" TEXT)";
        db.execSQL(sql);
        addQuestions();
        //db.close();
    }

    private void addQuestions()
    {
        Question q1=new Question("Which data type is used to create a variable that should store text in Java?","1/5", "Txt", "string ", "String", "String");
        this.addQuestion(q1);
        Question q2=new Question("Which method can be used to find the length of a string in Java?","2/5" ,"length()", "len()", "getLength()", "length()");
        this.addQuestion(q2);
        Question q3=new Question("Array indexes start with:","3/5", "1", "0","-1", "0" );
        this.addQuestion(q3);
        Question q4=new Question("How do you call a method in Java?","4/5", "methodName();", "(methodName);", "methodName;","methodName();");
        this.addQuestion(q4);
        Question q5=new Question("How to declare a numerical value 5","5/5", "x = 5;","integer x = 5;","int x = 5;","int x = 5;");
        this.addQuestion(q5);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.MovieEntry.TABLE_QUEST);
        // Create tables again
        onCreate(db);
    }
    // Adding new question
    public void addQuestion(Question quest) {
        //SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(QuizContract.MovieEntry.KEY_QUES, quest.getQUESTION());
        values.put(QuizContract.MovieEntry.COUNTER, quest.getCnt());
        values.put(QuizContract.MovieEntry.KEY_ANSWER, quest.getANSWER());
        values.put(QuizContract.MovieEntry.KEY_OPTA, quest.getOPTA());
        values.put(QuizContract.MovieEntry.KEY_OPTB, quest.getOPTB());
        values.put(QuizContract.MovieEntry.KEY_OPTC, quest.getOPTC());
        // Inserting Row
        dbase.insert(QuizContract.MovieEntry.TABLE_QUEST, null, values);
    }
    public List<Question> getAllQuestions() {
        List<Question> quesList = new ArrayList<Question>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + QuizContract.MovieEntry.TABLE_QUEST;
        dbase=this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Question quest = new Question();
                quest.setID(cursor.getInt(0));
                quest.setQUESTION(cursor.getString(1));
                quest.setCnt(cursor.getString(2));
                quest.setANSWER(cursor.getString(3));
                quest.setOPTA(cursor.getString(4));
                quest.setOPTB(cursor.getString(5));
                quest.setOPTC(cursor.getString(6));
                quesList.add(quest);
            } while (cursor.moveToNext());
        }
        // return quest list
        return quesList;
    }
    public int rowcount()
    {
        int row=0;
        String selectQuery = "SELECT  * FROM " + QuizContract.MovieEntry.TABLE_QUEST;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        row=cursor.getCount();
        return row;
    }
}
