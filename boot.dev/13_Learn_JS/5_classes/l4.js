class Contact {
    constructor(name, phoneNumber) {
        this.name = name;
        this._phoneNumber = phoneNumber;
    }

    set phoneNumber(value) {

        if (value.length != 10) throw new Error("Invalid phone number");
        else this._phoneNumber = value;
    }

    get phoneNumber() {

        return `(${this._phoneNumber.substring(0, 3)}) ` +
            `${this._phoneNumber.substring(3, 6)}-` +
            `${this._phoneNumber.substring(6, 10)}`
    }
}

const patrick = new Contact("Patrick", "1234567890");
console.log(patrick.phoneNumber);
// patrick.phoneNumber = "12345678901";
patrick.phoneNumber = "0987654321";
console.log(patrick.phoneNumber);

// don't touch below this line

export { Contact };
