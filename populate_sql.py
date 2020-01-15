import pandas as pd 
import sqlite3

db_loc= "C:/SOFTWARE_KIT/sqlite-tools/db/sample.db"
con = sqlite3.connect(db_loc)
cursor = con.cursor()
table_name="hate_data"

def is_table(table):
    table="'"+table+"'"
    query_string = f"SELECT name FROM sqlite_master WHERE type='table' AND name= {table}"
    return query_string

if not(len(cursor.execute(is_table(table_name)).fetchall())>0):
    pattern = "(id integer, count integer, hate_speech integer, offensive_language integer, neither integer, class integer , tweet text)"
    cursor.execute(f"create table {table_name} {pattern}")
    con.commit()

def insert_entry(table,array):
    base_string = f"insert into {table} values ({array[0]}, {array[1]}, {array[2]}, {array[3]}, {array[4]}, {array[5]}, {array[6]})"
    cursor.execute(base_string)
    con.commit()

csv_file=pd.read_csv("data.csv")
for i in range(0,len(csv_file)):
    array_temp=list()
    for col in csv_file.columns:
        array_temp.append(csv_file.iloc[i][col])
    if "'" in array_temp[-1]:
        array_temp[-1]=array_temp[-1].replace("'","''")
    array_temp[-1]="'"+array_temp[-1]+"'"
    insert_entry(table_name,array_temp)

con.commit()
con.close()
print("sqlite_populations_done")