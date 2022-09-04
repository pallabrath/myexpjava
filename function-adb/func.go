/**
 * @Author Pallab (pallab.rath@gmail.com)
 */

package main

import (
	"context"
	"database/sql"
	"encoding/json"
	"fmt"
	"io"

	fdk "github.com/fnproject/fdk-go"
	_ "github.com/godror/godror"
)

func main() {
	fdk.Handle(fdk.HandlerFunc(myHandler))
}

func myHandler(ctx context.Context, in io.Reader, out io.Writer) {
	db_user := "<db-user>"
	db_pwd := "<db-pwd>"
	db_host := "<db-host>"
	db_port := "<db-port>>"
	db_srvc := "<db-service>"

	db_details := fmt.Sprintf(`user="%s" password="%s" connectString="tcps://%s:%s/%s"`, db_user, db_pwd,
		db_host, db_port, db_srvc)
	db, err := sql.Open("godror", db_details)
	if err != nil {
		fmt.Println(err)
		return
	}
	defer db.Close()
	rows, err := db.Query("select sysdate from dual")
	if err != nil {
		fmt.Println("Error running query")
		fmt.Println(err)
		return
	}
	defer rows.Close()
	var resData string
	for rows.Next() {
		rows.Scan(&resData)
	}
	json.NewEncoder(out).Encode(&resData)
}
