
import javax.swing.*;


public class eeeeee extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public eeeeee(Composite parent, int style) {
		super(parent, style);
		
		Label lblWsh = new Label(this, SWT.NONE);
		lblWsh.setBounds(175, 126, 70, 20);
		lblWsh.setText("Wsh");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
