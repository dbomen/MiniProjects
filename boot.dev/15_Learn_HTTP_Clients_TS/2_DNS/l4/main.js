function getDomainNameFromURL(url) {
    const urlObj = new URL(url);
    return urlObj.hostname;
}
// TEST
console.log(getDomainNameFromURL("https://boot.dev/courses/learn-python"));
// don't touch below this line
export { getDomainNameFromURL };
