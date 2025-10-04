type ImageAttachment = {
    fileSize: number;
    width: number;
    height: number;
};

type DocumentAttachment = {
    fileSize: number;
    numPages: number;
};

type FileAttachment = {
    fileSize: number;
};

export type Attachment = ImageAttachment | DocumentAttachment | FileAttachment;

export function processAttachment(attachment: Attachment) {
    if ("width" in attachment) {
        return `Attached image, size: ${attachment.fileSize}, dimensions: ${attachment.width}x${attachment.height}`;
    }
    else if ("numPages" in attachment) {
        return `Attached document, size: ${attachment.fileSize}, pages: ${attachment.numPages}`;
    }
    else {
        return `Attached file, size: ${attachment.fileSize}`;
    }
}
