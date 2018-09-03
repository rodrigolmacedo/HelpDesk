function validateName(e) {
    var chr = String.fromCharCode(e.which);
    if (!/[a-zA-Z0-9~.\x22(),:;<>@[\]!#$%&\x27*+-=\/?^_`{|}\s]/.test(chr))
        return false;
}
function validateDescription(e) {
    var chr = String.fromCharCode(e.which);
    if (!/[a-zA-Z0-9~.\x22(),:;<>@[\]!#$%&\x27*+-=\/?^_`{|}\s]/.test(chr))
        return false;
}