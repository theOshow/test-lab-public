# test-lab-public
Euroland exam

URI specs

1) Get Number of Rows (count) and Columns (count)
//records/totals

2) Get all records from List<Table> array, returning converted object into JSON format matching table header names 
/records/

3) Same as 2. but sorted by “Batch”(ASC), by “Class”(ASC) and by last letter of “Name” (DESC)
/records/sorted/Batch/asc
/records/sorted/Class/desc/lastletter

uri accepts parameters for column name: 

/records/sorted/{column}/asc
/records/sorted/{column}/desc
/records/sorted/{column}/desc/lastletter

4) Same as 2. but group and sorted by “Batch” (DESC) with members and count of members
/records/grouped/Batch/desc

paremeters: column:
/records/grouped/{column}/desc

5) Find record(s) filtered by “Column  1” and  “Column 2” values – POST Parameters JSON Array
        a. Structure Param = [{“col_1”:”JAMES”, “col_2”:”10th”}]
/records/search
post body:

[{
    "col_1": "Richerd",
    "col_2": "10th"
   
},
{
    "col_1": "James",
    "col_2": "10th"  
}
]
