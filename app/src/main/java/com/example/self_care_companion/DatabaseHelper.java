package com.example.self_care_companion;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "SelfCareCompanionApp.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // User Table
        db.execSQL("CREATE TABLE User (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "first_name TEXT, " +
                "email TEXT UNIQUE, " +
                "pin TEXT)");

        // Mood Table
        db.execSQL("CREATE TABLE Mood (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "timestamp DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                "mood TEXT)");

        // Journal Table
        db.execSQL("CREATE TABLE Journal (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "timestamp DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                "entry TEXT)");

        // Habit Table
        db.execSQL("CREATE TABLE Habit (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "label TEXT, " +
                "value INTEGER, " +
                "timestamp DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                "units TEXT)");

        db.execSQL("CREATE INDEX idx_mood_timestamp ON Mood(timestamp)");
        db.execSQL("CREATE INDEX idx_journal_timestamp ON Journal(timestamp)");
        db.execSQL("CREATE INDEX idx_habit_timestamp ON Habit(timestamp)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS User");
        db.execSQL("DROP TABLE IF EXISTS Mood");
        db.execSQL("DROP TABLE IF EXISTS Journal");
        db.execSQL("DROP TABLE IF EXISTS Habit");
        onCreate(db);
    }

    public void addUser(String firstName, String email, String pin) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("first_name", firstName);
        values.put("email", email);
        values.put("pin", hashPin(pin));
        db.insert("User", null, values);
        db.close();
    }

    public void addMood(String mood) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("mood", mood);
        db.insert("Mood", null, values);
        db.close();
    }

    public void addJournalEntry(String entry) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("entry", entry);
        db.insert("Journal", null, values);
        db.close();
    }

    public void addHabit(String label, int value, String units) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("label", label);
        values.put("value", value);
        values.put("units", units);
        db.insert("Habit", null, values);
        db.close();
    }

    public String getMostFrequentMood() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT mood, COUNT(*) as count FROM Mood " +
                       "WHERE timestamp >= datetime('now', '-3 days') " +
                       "GROUP BY mood ORDER BY count DESC LIMIT 1";
        Cursor cursor = db.rawQuery(query, null);
        String mostFrequentMood = null;

        if (cursor.moveToFirst()) {
            mostFrequentMood = cursor.getString(0);
        }
        cursor.close();
        db.close();
        return mostFrequentMood;
    }

    public boolean checkifUserExists() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT COUNT(*) FROM User";
        Cursor cursor = db.rawQuery(query, null);
        boolean exists = false;

        if (cursor.moveToFirst()) {
            exists = cursor.getInt(0) > 0;
        }
        cursor.close();
        db.close();
        return exists;
    }

    public static String hashPin(String pin) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(pin.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 not supported", e);
        }
    }

    public String getUserPin() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT pin FROM User LIMIT 1";
        Cursor cursor = db.rawQuery(query, null);
        String pin = null;
        if (cursor.moveToFirst()) {
            pin = cursor.getString(0);
        }
        cursor.close();
        db.close();
        return pin;
    }
}

