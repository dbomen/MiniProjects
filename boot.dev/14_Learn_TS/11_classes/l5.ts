export abstract class Customer {
    firstName: string;
    lastName: string;
    protected balance: number;

    constructor(firstName: string, lastName: string, balance: number) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
    }

    getFullName(): string {
        return `${this.firstName} ${this.lastName}`;
    }
}

export class RegularCustomer extends Customer {
    constructor(firstName: string, lastName: string, balance: number) {
        super(firstName, lastName, balance)
    }

    getBalance(): number {
        return this.balance;
    }
}
