package main

import (
	"fmt"
	"io/ioutil"
	"net/http"
	"time"
)

func main() {

	i := int32(time.Now().Unix())

	fmt.Printf("aaa %d\n", i)

	result := Get("http://httpbin.org/get")

	println(result)

}

func Get(url string) string {
	res, err := http.Get(url)
	if err != nil {
		return "nil"
	}
	robots, err := ioutil.ReadAll(res.Body)
	res.Body.Close()
	if err != nil {
		return "nil"
	}
	return string(robots)
}
