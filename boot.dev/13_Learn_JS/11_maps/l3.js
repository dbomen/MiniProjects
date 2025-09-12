function fixUserMap(brokenMap) {

    const map = new Map();
    for (let mapObj of brokenMap) {
        const key = mapObj[0].fname + " " + mapObj[0].lname;
        map.set(key, mapObj[1]);
    }

    return map;
}

// TEST
console.log(fixUserMap(new Map([
    [
        { fname: "Eren", lname: "Yeager", tags: ["Survey Corps"] },
        { fname: "Eren", lname: "Yeager", tags: ["Survey Corps"] },
    ]])));

export { fixUserMap };
