async function fetchIPAddress(domain) {
    const resp = await fetch(`https://1.1.1.1/dns-query?name=${domain}&type=A`, {
        headers: {
            accept: "application/dns-json",
        },
    });
    const respObject = await resp.json();
    //console.log(respObject);
    return respObject.Answer[0].data;
}
// TEST
console.log(await fetchIPAddress("example.com"));
// don't touch below this line
export { fetchIPAddress };
