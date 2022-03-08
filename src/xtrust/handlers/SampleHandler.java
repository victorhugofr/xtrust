package xtrust.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

//import annotations.XPathA;

import org.eclipse.jface.dialogs.MessageDialog;
public class SampleHandler extends AbstractHandler {

//	@XPathA
//	public String xpath = "/b";
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		MessageDialog.openInformation(
				window.getShell(),
				"Xtrust",
				"Hello, Eclipse world");
		return null;
	}
}
