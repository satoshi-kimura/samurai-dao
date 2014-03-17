package jp.dodododo.dao.log4j;

import java.io.Serializable;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.log4j.Appender;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.WriterAppender;
import org.apache.log4j.helpers.QuietWriter;
import org.apache.log4j.spi.ErrorHandler;
import org.apache.log4j.spi.LoggingEvent;

/**
 * @author Satoshi Kimura
 *
 */
public class MemoryAppender extends WriterAppender implements Serializable {
	private static final long serialVersionUID = -13217246139823362L;
	private static final int MESSAGE_SIZE = 100;
	private List<Object> messageList = new FixedSizeList(MESSAGE_SIZE);
	private List<Object> formatMessageList = new FixedSizeList(MESSAGE_SIZE);

	public MemoryAppender() {
		this(null);
	}

	@Override
	public void append(LoggingEvent event) {
		super.append(event);
		messageList.add(event.getMessage());
		formatMessageList.add(layout.format(event));
	}

	public static Object getMessage(Class<?> clazz, int i) {
		@SuppressWarnings("unchecked")
		Enumeration<Appender> enumeration = Logger.getLogger(clazz).getAllAppenders();
		for (; enumeration.hasMoreElements();) {
			Appender appender = enumeration.nextElement();
			if (appender instanceof MemoryAppender) {
				MemoryAppender memoryAppender = (MemoryAppender) appender;
				return memoryAppender.getMessage(i);
			}
		}
		return null;
	}

	public static List<Object> getMessages(Class<?> clazz) {
		@SuppressWarnings("unchecked")
		Enumeration<Appender> enumeration = Logger.getLogger(clazz).getAllAppenders();
		for (; enumeration.hasMoreElements();) {
			Appender appender = enumeration.nextElement();
			if (appender instanceof MemoryAppender) {
				MemoryAppender memoryAppender = (MemoryAppender) appender;
				return memoryAppender.formatMessageList;
			}
		}
		return null;
	}

	public static void clear(Class<?> clazz) {
		@SuppressWarnings("unchecked")
		Enumeration<Appender> enumeration = Logger.getLogger(clazz).getAllAppenders();
		for (; enumeration.hasMoreElements();) {
			Appender appender = enumeration.nextElement();
			if (appender instanceof MemoryAppender) {
				MemoryAppender memoryAppender = (MemoryAppender) appender;
				memoryAppender.messageList.clear();
				memoryAppender.formatMessageList.clear();
			}
		}
	}

	public Object getMessage(int i) {
		return messageList.get(messageList.size() - 1 - i);
	}

	public Object getFormatMessage(int i) {
		return formatMessageList.get(formatMessageList.size() - 1 - i);
	}

	public MemoryAppender(Layout layout) {
		super();
		this.layout = layout;
		setWriter(new StringWriter());
	}

	@Override
	public synchronized void setWriter(Writer writer) {
		reset();
		qw = new MAQuietWriter(writer, errorHandler);
		writeHeader();
	}

	@Override
	public void activateOptions() {
		setWriter(new StringWriter());
	}

	@Override
	protected void closeWriter() {
		// empty
	}

	public class MAQuietWriter extends QuietWriter {
		private List<String> stringList = new ArrayList<String>();

		public MAQuietWriter(Writer writer, ErrorHandler errorHandler) {
			super(writer, errorHandler);
		}

		@Override
		public void write(String string) {
			super.write(string);
			stringList.add(string);
		}

		public String getString(int i) {
			return stringList.get(stringList.size() - 1 - i);
		}
	}

	public class FixedSizeList extends ArrayList<Object> {
		private static final long serialVersionUID = -3043536882058774414L;
		private int size;

		public FixedSizeList(int size) {
			super(size);
			this.size = size;
		}

		@Override
		public boolean add(Object o) {
			if (size() >= size) {
				remove(0);
			}
			return super.add(o);
		}
	}
}
