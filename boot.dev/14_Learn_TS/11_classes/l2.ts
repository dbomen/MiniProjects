export class Customer {
    firstName: string;
    lastName: string;
    #balance: number;

    constructor(firstName: string, lastName: string, balance: number) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.#balance = balance;
    }

    getFullName(): string {
        return `${this.firstName} ${this.lastName}`;
    }

    purchase(cost: number) {
        this.#balance -= cost;
        return this.#balance;
    }
}

// TEST
let customer: Customer = new Customer("John", "Wick", 100);
console.log(customer.purchase(10));
console.log(customer.purchase(50));
