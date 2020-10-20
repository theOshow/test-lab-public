# test-lab-public
Euroland exam

URI specs

1)
/records/totals

2)
/records/

3)
/records/sorted/Batch/asc
/records/sorted/Class/desc/lastletter

uri accepts parameters for column name: 

/records/sorted/{column}/asc
/records/sorted/{column}/desc
/records/sorted/{column}/desc/lastletter

4)
/records/grouped/Batch/desc

paremeters: column:
/records/grouped/{column}/desc


5) /records/search
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
