"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.processAttachment = processAttachment;
function processAttachment(attachment) {
    if ("width" in attachment) {
        return "Attached image, size: ".concat(attachment.fileSize, ", dimensions: ").concat(attachment.width, "x").concat(attachment.height);
    }
    else if ("numPages" in attachment) {
        return "Attached document, size: ".concat(attachment.fileSize, ", pages: ").concat(attachment.numPages);
    }
    else {
        return "Attached file, size: ".concat(attachment.fileSize);
    }
}
