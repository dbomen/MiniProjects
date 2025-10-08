export class ExecutiveMember {
    constructor(
        protected balance: number,
        protected points: number,
    ) { }

    isVip() {
        return (this.balance >= 10000)
    }

    redeemPoints(amount: number) {
        if (this.points >= amount) {
            this.points -= amount;
            return true;
        }
        return false
    }
}

// TEST
let member: ExecutiveMember = new ExecutiveMember(9999, 1);
console.log(member.isVip());
console.log(member.redeemPoints(2));
