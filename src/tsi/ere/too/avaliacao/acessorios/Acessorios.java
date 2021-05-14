package tsi.ere.too.avaliacao.acessorios;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Acessorios {
	/**
	 * pega a data atual do sistema e a retorna como uma string
	 * @return uma <code>String</code> com a data atual
	 */
	public static String dataAtualtoString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar calendar = Calendar.getInstance();
		return sdf.format(calendar.getTime());
	}
	
	public static String horaAtualtoString() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		Calendar calendar = Calendar.getInstance();
		return sdf.format(calendar.getTime());
	}
}
