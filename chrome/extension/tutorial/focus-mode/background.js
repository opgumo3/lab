chrome.runtime.onInstalled.addListener(() => {
  chrome.action.setBadgeText({
    text: "OFF",
  });
  setBadgeColorToRed()
});

const extensions = 'https://developer.chrome.com/docs/extensions';
const webstore = 'https://developer.chrome.com/docs/webstore';

// 작업에 팝업이 있는 경우 이 이벤트는 실행되지 않음.
chrome.action.onClicked.addListener(async (tab) => { 
  if (tab.url.startsWith(extensions) || tab.url.startsWith(webstore)) {
    // Retrieve the action badge to check if the extension is 'ON' or 'OFF'
    const prevState = await chrome.action.getBadgeText({ tabId: tab.id });
    // Next state will always be the opposite
    const nextState = prevState === 'ON' ? 'OFF' : 'ON';

        // Set the action badge to the next state
    await chrome.action.setBadgeText({
      tabId: tab.id,
      text: nextState,
    });

    if (nextState === "ON") {
      // Insert the CSS file when the user turns the extension on
      await chrome.scripting.insertCSS({
        files: ["focus-mode.css"],
        target: { tabId: tab.id },
      });
      setBadgeColorToGreen()
    } else if (nextState === "OFF") {
      // Remove the CSS file when the user turns the extension off
      await chrome.scripting.removeCSS({
        files: ["focus-mode.css"],
        target: { tabId: tab.id },
      });
      setBadgeColorToRed()
    }
  }
});

function setBadgeColorToGreen() {
  chrome.action.setBadgeBackgroundColor(
    { color: 'green' },
  );
}

function setBadgeColorToRed() {
  chrome.action.setBadgeBackgroundColor(
    { color: 'red' },
  );
}