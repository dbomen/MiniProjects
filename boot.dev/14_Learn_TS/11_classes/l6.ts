interface User {
    firstName: string;
    lastName: string;

    getFullName(): string;
}

interface Customer {
    getBalance(): number;
}

export class RegularCustomer implements User, Customer {
    firstName: string;
    lastName: string;
    protected balance: number;

    constructor(firstName: string, lastName: string, balance: number) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
    }

    getFullName() {
        return `${this.firstName} ${this.lastName}`;
    }

    getBalance() {
        return this.balance;
    }
}
