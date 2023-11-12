package A07_Sorting;

import java.util.Comparator;

public class Person {
	
	private final String nachname;
	
	private final String vorname;

	public Person(String vorname, String nachname) {
		this.nachname = nachname;
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}
	
	public String getVorname() {
		return vorname;
	}
	
	/**
	 * Vergleicht zwei Personen miteinander
	 * @return <0, wenn a<b || =0, wenn a=b || >0, wenn a>b
	 */
	public int compareTo(Person p) {
		return Comparator
				.comparing(Person::getNachname, String::compareTo)
				.thenComparing(Person::getVorname, String::compareTo)
				.compare(this, p);
	}
}
