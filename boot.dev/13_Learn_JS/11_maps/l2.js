function createUserMap(users) {

    const map = new Map();
    for (let obj of users) {
        const key = obj.fname + " " + obj.lname;
        map.set(key, obj);
    }

    return map;
}

// TEST
console.log(createUserMap(
    [
        {
            fname: "Clark",
            lname: "Kent",
            promos: ["promo1", "promo2"]
        },
        {
            fname: "Peter",
            lname: "Parker",
            promos: []
        }
    ]));

export { createUserMap };
