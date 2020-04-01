package main

import (
	"fmt"
	"time"
)

const (
	ANSIC       = "Mon Jan _2 15:04:05 2006"
	UnixDate    = "Mon Jan _2 15:04:05 MST 2006"
	RubyDate    = "Mon Jan 02 15:04:05 -0700 2006"
	RFC822      = "02 Jan 06 15:04 MST"
	RFC822Z     = "02 Jan 06 15:04 -0700" // RFC822 with numeric zone
	RFC850      = "Monday, 02-Jan-06 15:04:05 MST"
	RFC1123     = "Mon, 02 Jan 2006 15:04:05 MST"
	RFC1123Z    = "Mon, 02 Jan 2006 15:04:05 -0700" // RFC1123 with numeric zone
	RFC3339     = "2006-01-02T15:04:05Z07:00"
	RFC3339Nano = "2006-01-02T15:04:05.999999999Z07:00"
	Kitchen     = "3:04PM"
	// Handy time stamps.
	Stamp      = "Jan _2 15:04:05"
	StampMilli = "Jan _2 15:04:05.000"
	StampMicro = "Jan _2 15:04:05.000000"
	StampNano  = "Jan _2 15:04:05.000000000"

	// Date
	A = "2006-01-02"  // yyyy-MM-dd
	B = "20060102"    // yyyyMMdd
	C = "02-Jan-2006" // dd-MMM-yyyy
	D = "01/02/06"    // MM/dd/yy
	E = "01/02/2006"  // MM/dd/yyyy
	F = "010206"      // MMddyy
	G = "Jan-02-06"   // MMM-dd-yy
	H = "Jan-02-2006" // MMM-dd-yyyy
	K = "06"          // yy
	L = "Mon"         // EEE
	Q = "Monday"      // EEEE
	W = "Jan-06	"     // MMM-yy

	// Time
	P = "15:04"       // HH:mm
	R = "15:04:05"    // HH:mm:ss
	T = "3:04 PM"     // K:mm a
	Y = "03:04:05 PM" // KK:mm:ss a
)

func GetCurrentTime() *time.Time {
	time := time.Now()
	return &time
}

func ToDateTime(t int64) *time.Time {
	dt := time.Unix(t, 0)
	return &dt
}

func ToEpochSecond(t time.Time) int64 {
	return t.Unix()
}

func Add(y, m, d int, t time.Time) *time.Time {
	date := t.AddDate(y, m, d)
	return &date
}

func AddHours(t time.Time, h int) *time.Time {
	duration := time.Duration(h) * time.Hour
	result := t.Add(duration)
	return &result
}

func AddMinutes(t time.Time, m int) *time.Time {
	duration := time.Duration(m) * time.Minute
	result := t.Add(duration)
	return &result
}

func AddSeconds(t time.Time, s int) *time.Time {
	duration := time.Duration(s) * time.Second
	result := t.Add(duration)
	return &result
}

func AddDays(t time.Time, d int) *time.Time {
	date := t.AddDate(0, 0, d)
	return &date
}
func AddMonths(m int, t time.Time) *time.Time {
	date := t.AddDate(0, m, 0)
	return &date
}

func AddYears(t time.Time, y int) *time.Time {
	date := t.AddDate(y, 0, 0)
	return &date
}

func Format(layout string, t time.Time) string {
	return t.Format(layout)
}

func Parse(layout, val string) *time.Time {
	parse, err := time.Parse(layout, val)
	if err != nil {
		return nil
	}
	return &parse
}

func Sub(t1 *time.Time, t2 time.Time) int {
	duration := t1.Sub(t2)
	return int(duration.Hours() / 24)
}

func main() {
	fmt.Println(GetCurrentTime())

}
