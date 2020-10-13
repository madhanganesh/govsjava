package main

import (
	"encoding/json"
	"fmt"
	"time"
)

// JSONType struct
type JSONType time.Time

// MarshalJSON method
func (t JSONType) MarshalJSON() ([]byte, error) {
	stamp := fmt.Sprintf("\"%s\"", time.Time(t).Format("Mon Jan 2"))
	return []byte(stamp), nil
}

// Animal struct
type Animal struct {
	Name string `json:"name"`
}

// Dog struct
type Dog struct {
	Animal
	BarkVolume float32 `json:"barkVolume"`
}

// Cat struct
type Cat struct {
	Animal
	likesCream bool
	Lives      int      `json:"lives"`
	Dob        JSONType `json:"dob"`
}

func main() {
	d := Dog{
		Animal:     Animal{Name: "bruno"},
		BarkVolume: 3.2,
	}

	data, err := json.Marshal(d)
	if err != nil {
		panic(err)
	}

	fmt.Printf("%s\n", data)

	c := Cat{
		Animal:     Animal{"fluffy"},
		likesCream: true,
		Lives:      20,
		Dob:        JSONType(time.Now()),
	}

	data, err = json.Marshal(c)
	if err != nil {
		panic(err)
	}

	fmt.Printf("%s\n", data)

	/*var c1 Cat
	err = json.Unmarshal([]byte(data), &c1)
	if err != nil {
		panic(err)
	}

	fmt.Printf("%+v\n", c1)*/
}
