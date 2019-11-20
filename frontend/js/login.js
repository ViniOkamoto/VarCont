$(function() {
    setBackgroundSize();
})

function setBackgroundSize() {
    windowSize = $(window).height();
    $('.background').height(windowSize);
}