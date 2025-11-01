function getContentType(resp) {
    const content_type = resp.headers.get("Content-Type");
    if (content_type === null)
        return "";
    else
        return content_type;
}
// don't touch below this line
// TEST
const options = {
    headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer token123",
    }
};
const resp = new Response(null, options);
console.log(getContentType(resp));
const options1 = {
    headers: {
        Authorization: "Bearer token123"
    }
};
const resp1 = new Response(null, options1);
console.log(getContentType(resp1));
export { getContentType };
