package k.benasfirst;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


    public class DatabaseSQLite extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION   = 1;
    private static final String DATABASE_NAME   = "db";

            private static final String TABLE_USERS     = "users";
    private static final String USER_ID         = "id";
    private static final String USER_LEVEL      = "userlevel";
    private static final String USER_NAME       = "name";
    private static final String USER_PASSWORD   = "password";
    private static final String USER_EMAIL      = "email";

        private static final String TABLE_TARGET      = "Targets";
        private static final String TARGET_ID = "id";
    private static final String TARGET_NAME = "name";
    private static final String TARGET_HOSTILITY = "Hostility";
    private static final String TARGET_TODO   = "To do";
    private static final String TARGET_CAR  = "Car";
    private static final String POKEMON_WEIGHT      = "weight";
    private static final String POKEMON_HEIGHT      = "height";

            public DatabaseSQLite(Context context) {
                super(context, DATABASE_NAME, null, DATABASE_VERSION);
            }

            @Override
    public void onCreate(SQLiteDatabase db) {
                String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                                + USER_ID + " INTEGER PRIMARY KEY,"
                                + USER_LEVEL + " TEXT,"
                                + USER_NAME + " TEXT,"
                                + USER_PASSWORD + " TEXT,"
                                + USER_EMAIL + ")";
                String CREATE_TARGETS_TABLE = "CREATE TABLE " + TABLE_TARGET + "("
                                        + TARGET_ID + " INTEGER PRIMARY KEY,"
                                        + TARGET_NAME + " TEXT,"
                                        + TARGET_HOSTILITY + " TEXT,"
                                        + TARGET_TODO + " TEXT,"
                                        + TARGET_CAR + " TEXT,"
                                        + POKEMON_WEIGHT + " REAL,"
                                        + POKEMON_HEIGHT + ")";

                db.execSQL(CREATE_USERS_TABLE);
                db.execSQL(CREATE_TARGETS_TABLE);
            }

            @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
               // Drop older table if existed
                        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);

                        // Create tables again
                                onCreate(db);
            }

            void addUser(User user) {
                SQLiteDatabase db = this.getWritableDatabase();

                        ContentValues values = new ContentValues();
                values.put(USER_LEVEL,      user.getUserlevel());
                values.put(USER_NAME,       user.getUsernameForRegister());
                values.put(USER_PASSWORD,   user.getPasswordForRegister());
                values.put(USER_EMAIL,      user.getEmailForRegister());

                        // Inserting Row
                                db.insert(TABLE_USERS, null, values);

                        // Closing database connection
                                db.close();
           }

            User getUser(int id) {
                SQLiteDatabase db = this.getReadableDatabase();

                        Cursor cursor = db.query(
                                TABLE_USERS,
                                new String[]{
                                                USER_ID,
                                                USER_LEVEL,
                                                USER_NAME,
                                                USER_PASSWORD,
                                                USER_EMAIL
                                                },
                                USER_ID + "=?",
                                new String[]{String.valueOf(id)}, null, null, null, null);

                        if (cursor != null)
                        cursor.moveToFirst();

                        User user = new User(
                                cursor.getString(0),
                                cursor.getString(1),
                                cursor.getString(2),
                               cursor.getString(3)
                                );

                        return user;
            }

            public List<User> getAllUsers() {
                List<User> users = new ArrayList<User>();

                        // Select All Query
                                String selectQuery = "SELECT  * FROM " + TABLE_USERS;

                        SQLiteDatabase db = this.getWritableDatabase();

                        Cursor cursor = db.rawQuery(selectQuery, null);

                        // looping through all rows and adding to list
                        if (cursor.moveToFirst()) {
                        do {
                                User user = new User();

                                        user.setId(Integer.parseInt(cursor.getString(0)));
                                user.setUserlevel(cursor.getString(1));
                                user.setUsernameForRegister(cursor.getString(2));
                                user.setPasswordForRegister(cursor.getString(3));
                                user.setEmailForRegister(cursor.getString(4));

                                        // adding user to list
                                                users.add(user);
                            } while (cursor.moveToNext());
                    }

                        // return users list
                                return users;
            }
        void addPokemon(Target target) {
                    SQLiteDatabase db = this.getWritableDatabase();

                            ContentValues values = new ContentValues();
                    values.put(TARGET_NAME,        target.getName());
                    values.put(TARGET_HOSTILITY,   target.getHostility());
                    values.put(TARGET_TODO,   target.getToDo());
                    values.put(TARGET_CAR,        target.getCar());
                    values.put(POKEMON_WEIGHT,      target.getWeight());
                    values.put(POKEMON_HEIGHT,      target.getHeight());

                            // Inserting Row
                                    db.insert(TABLE_TARGET, null, values);

                            // Closing database connection
                                    db.close();
                }

            public List<Target> getAllTargets() {
                    List<Target> targets = new ArrayList<Target>();

                            // Select All Query
                                    String selectQuery = "SELECT  * FROM " + TABLE_TARGET;

                            SQLiteDatabase db = this.getWritableDatabase();
                            Cursor cursor = db.rawQuery(selectQuery, null);

                            // looping through all rows and adding to list
                                    if (cursor.moveToFirst()) {
                            do {
                                    Target target = new Target();

                                target.setId(Integer.parseInt(cursor.getString(0)));
                                target.setName(cursor.getString(1));
                                target.setHostility(cursor.getString(2));
                                target.setToDo(cursor.getString(3));
                                target.setCar(cursor.getString(4));
                                target.setWeight(cursor.getDouble(5));
                                target.setHeight(cursor.getDouble(6));

                                            // adding user to list
                                                    targets.add(target);
                                } while (cursor.moveToNext());
                        }

                            // return pokemonaiSQLite list
                                    return targets;
                }

            public List<Target> getTargetByName(String name) {
                    List<Target> targets = new ArrayList<Target>();

                            SQLiteDatabase db = this.getWritableDatabase();

                            Cursor cursor = db.rawQuery("SELECT * FROM pokemonai WHERE name LIKE '%"+name+"%'", null);

                            // looping through all rows and adding to list
                                    if (cursor.moveToFirst()) {
                            do {
                                    Target target = new Target();

                                target.setId(Integer.parseInt(cursor.getString(0)));
                                target.setName(cursor.getString(1));
                                target.setHostility(cursor.getString(2));
                                target.setToDo(cursor.getString(3));
                                target.setCar(cursor.getString(4));
                                target.setWeight(cursor.getDouble(5));
                                target.setHeight(cursor.getDouble(6));

                                            // adding user to list
                                                    targets.add(target);
                                } while (cursor.moveToNext());
                        }

                            // return pokemonaiSQLite list
                                    return targets;

                        }





        public boolean isValidUser(String username, String password){
                    Cursor c = getReadableDatabase().rawQuery(
                                    "SELECT * FROM " + TABLE_USERS + " WHERE "
                                                    + USER_NAME + "='" + username + "'AND " +
                                                    USER_PASSWORD + "='" + password + "'" , null);
                    if (c.getCount() > 0)
                            return true;
                    return false;
                }
}

