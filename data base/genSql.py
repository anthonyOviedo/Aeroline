import csv


def loadSqlFile():
    with open('Realairports.csv') as csv_file:
        csv_reader = csv.reader(csv_file, delimiter=',')
        line_count = 0
        for row in csv_reader:
            if line_count == 0:
                print(f'Column names are {", ".join(row)}')
                line_count += 1
            else:
                f = open("insertData.sql", "a")
                if str(row[0]).isalpha() and str(row[1]).isalpha() and str(
                        row[2]).isalpha():
                    sql = "\n insert into lab01_Location(id, city_name, airport_name,country) values({lc}, \'{city}', '{aport}' , '{country}'); ".format(
                        lc=line_count,
                        city=str(row[0]),
                        aport=str(row[1]),
                        country=str(row[2]))
                    print(sql)
                    f.write(sql)
                    f.close()
                    line_count += 1


loadSqlFile()