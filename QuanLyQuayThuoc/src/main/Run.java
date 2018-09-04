    package main;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import control.ControlGiaoDien;
import giaodien.GiaoDienDangNhap;


public class Run {
	//sasas
	public static void main(String[] args) {
		ControlGiaoDien control =new ControlGiaoDien();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try { 
					UIManager.setLookAndFeel(control.TaiChuDe());
					new GiaoDienDangNhap().setVisible(true);
				} 
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		
	}
	
}
